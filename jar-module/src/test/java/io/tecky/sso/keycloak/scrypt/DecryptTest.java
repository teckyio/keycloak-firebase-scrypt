package io.tecky.sso.keycloak.scrypt;

import com.smartmovesystems.hashcheck.FirebaseScrypt;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.credential.dto.PasswordCredentialData;
import org.keycloak.models.credential.dto.PasswordSecretData;

import java.security.GeneralSecurityException;

public class DecryptTest {

    @Test
    public void decryptViaScrypt() throws GeneralSecurityException {
//        uncomment below to test your key and password
//        boolean result = FirebaseScrypt.check(
//                "",
//                "",
//                "",
//                "",
//                "",
//                0,
//                0
//        );
//        Assert.assertTrue(result);
    }
}