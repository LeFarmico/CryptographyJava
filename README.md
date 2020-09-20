# CryptographyJava
Это учебный проект, с целю понять принципы работы системы шифрования в Java. Для данного проекта не использовались сторонние библиотеки, только те, что идут вместе с Java 14 версии. Использовался jdk-14.0.2. Для хранения ключей используется txt файл, выбор пал на него для большей наглядности.

## AES Шифрование
AES Шифрование это шифрование с симметричным ключом (для шифровки и расшифровки используется один и тот же ключ). Для создания ключа необходимо создать экземпляр шаблона создания ключа `AESTypeKey`.

    AESKeyType AESKey = new AESKeyType(key); //key - 16-ти символьный ключ
    
Для генерации ключа используется метод generate().

    AESKey.generete();

Экземпляр ключа находится в: `src\KeyStorage\secretAESKey.txt`
Далее создаем экземпляр класса KeyStorage и берем из хранилища нужный ключ secKey

    KeyStorage keyStorage = new KeyStorage();
    keyStorage.getSecKey();
    
Теперь имея ключ, можно истользовать шифрование. Для этого нам нужно создать экземпляр класса `MessageEncryption`, используя factory создаем экземпляр класса `AESCipher`.

    MessageEncryption AES = cipherFactory.getCipher(CipherType.AES);
    
Теперь можно использовать само шифрование, для этого вызываем метод `encryptMessage`. В нем используется `AES/CBC/PKCS5Padding` параметры шифрования.

    String AESEncrypted = AES.encryptMessage(message, keyStorage.getSecKey());

Мы получили зашифрованное сообщение, для рассшифровки которого нам нужно использовать тот же ключ в методе `decryptMessage`.

    String AESDecrypted = AES.decryptMessage(AESEncrypted, keyStorage.getSecKey());
    
Вот что мы получаем в итоге:

    System.out.println("Original message: " + message);
    System.out.println("Encrypted message: " + AESEncrypted);
    System.out.println("Decrypted message: " + AESDecrypted + "\n");
    
    Output:
    Original message: тот кого нельзя называть, вернулся и он очень очень зол
    Encrypted message: UiOnRNw8ccHgDY3Shj3WQkVlKZGU2Rmnthl+im0TGr5A/ag5Pjce1PXZ3yUFQUSDlLt3ZwzMqWZUlTpdg7xN8ftM8pE04dxq92An0N+ezcMQ3CoH2UBmTAb+Sw/i1ZII0otmS8JGgjpcfdxZOqpbKA==
    Decrypted message: тот кого нельзя называть, вернулся и он очень очень зол

## RSA Шифрование
RSA Шифрование это шифрование с ассиметричным ключем (Т.е для шифровки используется публичный ключ, для расшифровки используется приватный ключ).
Создаем шаблон ключа и также генерируем его с помощью метода generate()

    RSAKeyType RSAKey = new RSAKeyType(1024);
    RSAKey.generate();

Cоздаем экземпляр класса `MessageEncryption`, используя factory создаем экземпляр класса `RSACipher`. 

    MessageEncryption RSA = cipherFactory.getCipher(CipherType.RSA);

Берем из хранилища нужные ключи.

    keyStorage.getPubKey();
    keyStorage.getPrvKey();

Используем их для шифрования и дешифрования сообщения

    String RSAEncrypted = RSA.encryptMessage(message, keyStorage.getPubKey());
    String RSADecrypted = RSA.decryptMessage(RSAEncrypted, keyStorage.getPrvKey());

Вот что мы получаем в итоге:

    System.out.println("Original message: " + message);
    System.out.println("Encrypted message: " + RSAEncrypted);
    System.out.println("Decrypted message: " + RSADecrypted + "\n");
        
    Output:
    Original message: тот кого нельзя называть, вернулся и он очень очень зол
    Encrypted message: YhQUzXRayQ3uIeQRfjXWRtP0NCQ4cBUUCEQaZcSe5q41H1clpKrA0ZqpOOCajakorja5aK9DWFPVMU0OMupXqklmWLJXEvnUVyt9g/RrY2ob77Ovq8RSYwAEPT96XWWaD39XrWUcKCAWvKVKsS5IUefu9x1XpnJA0EhFVvidT5E=
    Decrypted message: тот кого нельзя называть, вернулся и он очень очень зол

## Кастомный тип шифрования
Для создания кастомного типа шифрования и использовал способ `обратного или` на каждый байт в строке сообщения и на каждый байт ключа, Ключом исаользовал изображение формата `png`.
Cоздаем экземпляр класса `MessageEncryption`, используя factory создаем экземпляр класса `IMGCipher`. 

    MessageEncryption IMG = cipherFactory.getCipher(CipherType.IMG);

В данном случае можно сразу извлекать наше изображение в хранилище

    keyStorage.getImgKey()

Теперь мы можем зашифровать и расшифровать сообщение.

    String IMGEncrypted = IMG.encryptMessage(message, keyStorage.getImgKey());
    String IMGDecrypted = IMG.decryptMessage(IMGEncrypted, keyStorage.getImgKey());

Проверяем что получилось:

    System.out.println("Original message: " + message);
    System.out.println("Encrypted message: " + IMGEncrypted);
    System.out.println("Decrypted message: " + IMGDecrypted + "\n");
    
    Output:
    Original message: тот кого нельзя называть, вернулся и он очень очень зол
    Encrypted message: WNKe+dyIOtq60L7d+pj6ctC90lvQu9BB2LHRjyCRcC5/0LdRi5n2keSpWD1xxZP11UmS2X5Oaiwfq9SVqfWLaFCc9tQPPzZgkPxDo00sRSgI3/UD4wRSttC9gOzqQ+aB5ogPXQ==
    Decrypted message: тот кого нельзя называть, вернулся и он очень очень зол
