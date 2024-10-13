function encryptedPassword(password){
	var passwordEncode = password.split("").reverse().join("");
	//老的加密算法有问题，使用新的实现方法
	 var publicKeyExponent="%s";
	 var publicKeyModulus="%s";
	 RSAUtils.setMaxDigits(400);
	 var key = new RSAUtils.getKeyPair(publicKeyExponent, "", publicKeyModulus); 
	 var passwordEncry = RSAUtils.encryptedString(key,passwordEncode);//这里要对字符串进行反转，否则解密的密码是反的
	 
	 return passwordEncry;
}