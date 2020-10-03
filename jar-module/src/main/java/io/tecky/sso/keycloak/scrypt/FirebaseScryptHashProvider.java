package io.tecky.sso.keycloak.scrypt;

import com.lambdaworks.crypto.SCrypt;
import com.smartmovesystems.hashcheck.FirebaseScrypt;
import org.apache.commons.codec.binary.Base64;
import org.keycloak.Config;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.credential.hash.PasswordHashProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.PasswordPolicy;
import org.keycloak.models.credential.PasswordCredentialModel;

import java.security.GeneralSecurityException;

public class FirebaseScryptHashProvider implements PasswordHashProvider, PasswordHashProviderFactory {
    public static final String ID = "scrypt";

    private String saltSep= "";
    private String signerKey = "";
    private int rounds = 0;
    private int memCost = 0;

    public boolean policyCheck(PasswordPolicy passwordPolicy, PasswordCredentialModel passwordCredentialModel) {
        return ID.equals(passwordCredentialModel.getPasswordCredentialData().getAlgorithm());
    }

    public PasswordCredentialModel encodedCredential(String s, int iterations) {
        return null;
    }

    public boolean verify(String plainTextPassword, PasswordCredentialModel passwordCredentialModel) {
        try {
            return FirebaseScrypt.check(
                plainTextPassword,
                passwordCredentialModel.getPasswordSecretData().getValue(),
                Base64.encodeBase64String(passwordCredentialModel.getPasswordSecretData().getSalt()),
                saltSep,
                signerKey,
                rounds,
                memCost
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PasswordHashProvider create(KeycloakSession keycloakSession) {
        return this;
    }

    public void init(Config.Scope config) {
        saltSep = config.get("salt_sep");
        signerKey = config.get("signer_key");
        rounds = Integer.parseInt(config.get("rounds"));
        memCost = Integer.parseInt(config.get("mem_cost"));
    }

    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    public void close() {

    }

    public String getId() {
        return ID;
    }
}
