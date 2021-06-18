# 1. Visão Geral

### 1.1 SDK de criptografia AWS para Java
O SDK de criptografia AWS permite criptografia segura do lado do cliente. Ele usa as melhores práticas de criptografia para proteger seus dados e as chaves de criptografia usadas para proteger esses dados. Cada objeto de dados é protegido com uma chave de criptografia de dados exclusiva (DEK), e a DEK é protegida com uma chave de criptografia de chave (KEK) chamada de chave mestra. A DEK criptografada é combinada com os dados criptografados em uma única mensagem criptografada, portanto, você não precisa controlar as DEKs para seus dados. O SDK oferece suporte a chaves mestras no AWS Key Management Service (KMS) e também fornece APIs para definir e usar outros provedores de chave mestra. O SDK fornece métodos para criptografar e descriptografar strings, matrizes de bytes e fluxos de bytes. Para obter detalhes, consulte o código de exemplo e o Javadoc.

# 2. Maven

```
<dependency>
  <groupId>com.amazonaws</groupId>
  <artifactId>aws-encryption-sdk-java</artifactId>
  <version>2.3.0</version>
</dependency>
```
