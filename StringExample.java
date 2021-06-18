// Este código de amostra criptografa e, em seguida, descriptografa uma string usando um KMS CMK
// Você fornece o ARN da chave KMS e a string de texto simples como argumentos
package com.amazonaws.crypto.examples;

import java.util.Collections;
import java.util.Map;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CommitmentPolicy;
import com.amazonaws.encryptionsdk.CryptoResult;
import com.amazonaws.encryptionsdk.kms.KmsMasterKey;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;

public class StringExample {
    private static String keyArn;
    private static String data;

    public static void main(final String[] args) {
        keyArn = args[0];
        data = args[1];

        // Instancie o SDK
        final AwsCrypto crypto = AwsCrypto.standard();

        // Configure o provedor de chave mestra
        final KmsMasterKeyProvider prov = KmsMasterKeyProvider.builder().buildStrict(keyArn);

        // Criptografar os dados
        //
        // NOTA: Os dados criptografados devem ter um contexto de criptografia associado
        // para proteger a integridade. Para este exemplo, basta usar um marcador
        // valor. Para obter mais informações sobre o contexto de criptografia, consulte
        // https://amzn.to/1nSbe9X (blogs.aws.amazon.com)
        final Map<String, String> context = Collections.singletonMap("Example", "String");

        final String ciphertext = crypto.encryptString(prov, data, context).getResult();
        System.out.println("Ciphertext: " + ciphertext);

        // Descriptografar os dados
        final CryptoResult<String, KmsMasterKey> decryptResult = crypto.decryptString(prov, ciphertext);
        // Verifique o contexto de criptografia (e de preferência a chave mestra) 
        // para garantir que este seja o texto cifrado esperado        
        if (!decryptResult.getMasterKeyIds().get(0).equals(keyArn)) {
            throw new IllegalStateException("Wrong key id!");
        }

        // O SDK pode adicionar informações ao contexto de criptografia, 
        // portanto, verifique se todos os valores estão presentes
        for (final Map.Entry<String, String> e : context.entrySet()) {
            if (!e.getValue().equals(decryptResult.getEncryptionContext().get(e.getKey()))) {
                throw new IllegalStateException("Wrong Encryption Context!");
            }
        }

        // Os dados estão corretos, então imprima-os.
        System.out.println("Decrypted: " + decryptResult.getResult());
    }
}
