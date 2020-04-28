package com.mautentication.autentication.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.conection.FirebaseConection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import com.mautentication.autentication.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class UserController {

	@PostMapping("user")
	public User login(@RequestParam("mail") String mail,
                      @RequestParam("mailAlt") String mailAlt,
                      @RequestParam("name") String name,
                      @RequestParam("photo") String photo,
                      @RequestParam("telephone") String telephone,
                      @RequestParam("uId") String uId) throws IOException {

		User user = new User(mail,mailAlt,name,photo,telephone,uId);

        //Conectando al firebase del proyecto

        FirebaseConection conection=new FirebaseConection();
        try {
            System.out.println(conection.Conection());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Conectando al firebase del proyecto

        //conectando al Firestore de la instancia de firebase
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("user").document(uId);
        //conectando al Firestore de la instancia de firebase

        //Añadiendo nuevo usuario
        Map<String, Object> data = new HashMap<>();
        data.put("mail", mail);
        data.put("mailAlt", mailAlt);
        data.put("name", name);
        data.put("photo",photo);
        data.put("telephone",telephone);
        data.put("uId",uId);
//asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
// ...
// result.get() blocks on response
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Añadiendo nuevo usuario
//------------------esto es una prueba de datos hacia Firebase-------------------//
		return user;
	}
    @PostMapping("Exiuser")
    public int login(
                      @RequestParam("uId") String uId) throws IOException {


        //Conectando al firebase del proyecto

        FirebaseConection conection=new FirebaseConection();
        try {
            System.out.println(conection.Conection());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Conectando al firebase del proyecto

        //conectando al Firestore de la instancia de firebase
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("user").document(uId);
        //conectando al Firestore de la instancia de firebase

        ApiFuture<DocumentSnapshot> future = docRef.get();

// ...
// future.get() blocks on response
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (document.exists()) {
            return 1;
        } else {
            return 0;
        }

    }
	private String getJWTToken(String username) {

		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
