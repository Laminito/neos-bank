package sn.neos.config

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
class JwtUtil {

 /*   @Value("${application.security.jwt.secret-secretKey}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;*/

      String expirationString;
      String secret = "WHNsamJSLXhScUtGYldBNmlwdC1YZ2lkREJwcmtyR3BkTz9FQ1JtdVphQmpGbTAzbUc2V1ctTUdxUXZ0ZVdMbVI5TG9vbUY/UnZaSEFQN0JhRk1QcjBLYnpnOHlUejhBcVFpVEdFU3lsUURIbVlFcWtUY1FTbXJ4QVQxT2dMQ0szeGZwMVBDS0RBazRGMWUvUEdKcVFENU9yS25jbDFXV1l2WFZPTklsMDNwM2swdDBzb0JoN0twd1lkOFdJUkNJLTI9bTR2b3YwNGFQNFU0cHRwME9tcjFFeDBnVGxFWGtZSkw9YSFIOGtJamxTV2NuUDZYIXBFV0xzUWJvb3dUPQ== "
      Long expiration = 3600;


    public String generateToken(String userId,String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration * 1000);

        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userId,username, now, expirationDate);
    }

    private String createToken(Map<String, Object> claims, String id,String subject, Date issuedAt, Date expirationDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    String getExpirationString() {
        return expirationString
    }

    String getSecret() {
        return secret
    }

    Long getExpiration() {
        return expiration
    }

    void setExpiration(Long expiration) {
        this.expiration = expiration
    }
}
