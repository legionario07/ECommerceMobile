package com.example.paulinho.ecommercemobile.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.api.RetrofitConfig;
import com.example.paulinho.ecommercemobile.api.services.ConfiguracaoServices;
import com.example.paulinho.ecommercemobile.api.services.impl.ConfiguracaoServicesImpl;
import com.example.paulinho.ecommercemobile.api.services.impl.MBLServicesImpl;
import com.example.paulinho.ecommercemobile.converters.ConverterToProduto;
import com.example.paulinho.ecommercemobile.model.Configuracao;
import com.example.paulinho.ecommercemobile.model.DataForUser;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.SessionUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UserLogin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private FirebaseAuth mFirebaseAuth;
    private GoogleSignInClient mGoogleSignIn;
    private ProgressDialog dialog;
    private List<Produto> produtos;
    private List<Configuracao> configuracoes;
    private ConfiguracaoServices services;


    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setBackgroundColor(SignInButton.COLOR_DARK);
        signInButton.setContentDescription("Sign In");
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        setText(signInButton);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignIn = GoogleSignIn.getClient(this, gso);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        if (getIntent().hasExtra("FINISH")) {
            mGoogleSignIn.revokeAccess()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
            finish();
        }

    }

    private void setText(SignInButton signInButton) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText("Sign In");
                return;
            }
        }
    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        dialog = new ProgressDialog(UserLogin.this);
        dialog.setMessage("Processando Itens...Aguarde...");
        dialog.setTitle("ECommerceVendas");
        dialog.show();

        startActivityForResult(intent, 1);
    }

    private void signOut() {
        mFirebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Toast.makeText(UserLogin.this, "conta desconectada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                getProdutos();
                getConfiguracoes();
                firebaseLogin(account);

            }
        }
    }


    private void getConfiguracoes() {

        Retrofit retrofit = RetrofitConfig.getBuilder(true);
        services = retrofit.create(ConfiguracaoServices.class);


        services.findAll().subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Configuracao>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(ConstraintUtils.LOG, "completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Configuracao> configuracoes) {
                        SessionUtil.getInstance().setConfiguracoes(configuracoes);
                        for (Configuracao c : configuracoes) {
                            SessionUtil.getInstance().getMapConfiguraces().put(c.getPropriedade(), c.getValor());
                        }

                    }
                });

        //VERIFICA SE TEM CONFIGURAÇOES PADRÕES
        try {
            verificarConfiguracoesPadroes();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void verificarConfiguracoesPadroes() throws IOException {
        ConfiguracaoServicesImpl services = new ConfiguracaoServicesImpl();

        if (!SessionUtil.getInstance().getMapConfiguraces().containsKey(ConstraintUtils.TARIFA_CLASSICO)) {
            Configuracao c = new Configuracao();
            c.setPropriedade(ConstraintUtils.TARIFA_CLASSICO);
            c.setValor("11");


            c = services.save(c);
        }


        if (!SessionUtil.getInstance().getMapConfiguraces().containsKey(ConstraintUtils.TARIFA_PREMIUM)) {
            Configuracao c = new Configuracao();
            c.setPropriedade(ConstraintUtils.TARIFA_PREMIUM);
            c.setValor("16");

            c = services.save(c);
        }


        if (!SessionUtil.getInstance().getMapConfiguraces().containsKey(ConstraintUtils.VALOR_PRODUTO_TAXA)) {
            Configuracao c = new Configuracao();
            c.setPropriedade(ConstraintUtils.VALOR_PRODUTO_TAXA);
            c.setValor("120");


            c = services.save(c);
        }

        if (!SessionUtil.getInstance().getMapConfiguraces().containsKey(ConstraintUtils.VALOR_VENDA_UNITARIA)) {
            Configuracao c = new Configuracao();
            c.setPropriedade(ConstraintUtils.VALOR_VENDA_UNITARIA);
            c.setValor("5");


            c = services.save(c);

        }

    }

    private void getProdutos() {

        MBLServicesImpl services = new MBLServicesImpl();
        DataForUser dados = services.getDataForUser(ConstraintUtils.CODIGO_CLIENTE);
        produtos = ConverterToProduto.getProductsForDataForUser(dados);

        SessionUtil.getInstance().setProdutos(produtos);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private void firebaseLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(UserLogin.this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        signIn();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(UserLogin.this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
    }
}
