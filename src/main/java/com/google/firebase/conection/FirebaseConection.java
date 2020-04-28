package com.google.firebase.conection;
import com.google.firebase.FirebaseApp;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public  class FirebaseConection {
    public FirebaseApp Conection() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/ayudaencasa-38fe2-firebase-adminsdk-nt19s-1b8c2c3f3b.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ayudaencasa-38fe2.firebaseio.com")
                .build();

        return FirebaseApp.getConection(options);
    }
}
