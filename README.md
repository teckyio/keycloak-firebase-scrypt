# Keycloak Firebase Scrypt Verifier

This is an SPI extension for Keycloak to verify hashed passwords imported from Firebase using the special version of Firebase Scrypt.

The Firebase Scrypt code is copied from https://github.com/SmartMoveSystems/firebase-scrypt-java 

**Note: `encodedCredential` hasn't been implemented.**

### Installation

1. Build the `.ear` (`mvn package`)

2. Edit and add below in the correct `<profile />` of `standalone.xml` or `domain.xml`
    
    ```
        <spi name="password-hashing">
            <provider name="scrypt" enabled="true">
                <properties>
                    <property name="salt_sep" value="YOUR_FIREBASE_SEP"/>
                    <property name="signer_key" value="YOUR_FIREBASE_SIGNER_KEY"/>
                    <property name="rounds" value="YOUR_FIREBASE_ROUNDS"/>
                    <property name="mem_cost" value="YOUR_FIREBASE_MEM_COST"/>
                </properties>
            </provider>
        </spi>
    ``` 

3. Go to the shell of JBoss CLI (`$KEYCLOAK_HOME/bin/jboss-cli.sh`) and execute below:
    
    ```
    deploy sso.keycloak.scrypt-bundle-1.0.0-SNAPSHOT.ear --all-server-groups
    ```

4. If there is no error message, you should have Firebase Scrypt installed successfully. You may double check with:

    ```
    :read-children-names(child-type=deployment)
    ```

## Tested Environment

* Ubuntu 18.04
* Keycloak 9.0.2
* OpenJDK 1.8.0_265

## Troubleshooting

* If there is `NumberFormatException` during deployment, that means the SPI properties in `standalone.xml` or `domain.xml` cannot be loaded. Double check if the configuration has been put in the correct `<profile />`. 