package cn.lzy.util;

import cn.lzy.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTUtil {
	
	private static final long EXPIRE_TIME = 15 * 60 * 1000;
	private static final String TOKEN_SECRET = "dsdsa221";
	
	/**
	 * 生成签名，15分钟过期
	 * @param **username**
	* @param **password**
	* @return
	 */
	public static String sign(User user) {
	    try {
	        // 设置过期时间
	        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
	        // 私钥和加密算法
	        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	        // 设置头部信息
	        Map<String, Object> header = new HashMap<>(2);
	        header.put("typ", "JWT");
	        header.put("alg", "HS256");
	        // 返回token字符串
	        return JWT.create()
	                .withHeader(header)
	                .withClaim("aud", user.getPhone())
	                .withClaim("uid", user.getId())
	                .withExpiresAt(date)
	                .sign(algorithm);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	   *      检验token是否正确
	 * @param **token**
	 * @return
	 */
	public static boolean verify(String token){
	    try {
	        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	        JWTVerifier verifier = JWT.require(algorithm).build();
	        verifier.verify(token);
	        return true;
	    } catch (Exception e){
	        return false;
	    }
	}
	
	/**
	   *      从token解析出uid信息
	 * @param token
	 * @return
	 */
	public static int parseTokenUid(String token) {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getClaim("uid").asInt();
	}
	
	public static void main(String[] args) {
		String token = "yJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhZG1pbiIsInVpZCI6MTAwNSwiZXhwIjoxNTkxNDg4OTg3fQ.mWcGb5vrLy2GRXhf8uYlmknFYuiTK77i8eSv5Ui4pnw";
		boolean a = verify(token);
		System.out.println(a);
		int  s = parseTokenUid(token);
		System.out.println(s);
	}
	
}
