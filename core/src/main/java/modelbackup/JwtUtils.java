package modelbackup;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JwtUtils {

    private static final long TOKEN_EXPIRATION_MS = 3600_000;  // 1 hour

    // Generate the secret key from username and password hash
    private static Algorithm getAlgorithm(String username, String passwordHash) {
        String combinedSecret = username + ":" + passwordHash;
        return Algorithm.HMAC256(combinedSecret);
    }

    public static String generateToken(String username, String passwordHash, int tokenVersion) {
        long nowMillis = System.currentTimeMillis();
        Algorithm algorithm = getAlgorithm(username, passwordHash);

        return JWT.create()
            .withSubject(username)
            .withClaim("tokenVersion", tokenVersion)
            .withIssuedAt(new Date(nowMillis))
            .withExpiresAt(new Date(nowMillis + TOKEN_EXPIRATION_MS))
            .sign(algorithm);
    }

    public static DecodedJWT verifyToken(String token, String username, String passwordHash) {
        Algorithm algorithm = getAlgorithm(username, passwordHash);

        JWTVerifier verifier = JWT.require(algorithm)
            .build();

        return verifier.verify(token);
    }
}
