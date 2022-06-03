package com.tdd.ex.ch2;

public class PasswordStrengthMeter {
	public PasswordStrength meter(String s) {
		if( s == null || s.isEmpty() ) return PasswordStrength.INVALID;
//		if(s.length() <8 ) {
//			return PasswordStrength.NORMAL;
//		}
		int metCounts = getMetCriteriaCounts(s);
//		if(s.length() >= 8) metCounts++; //길이
//		if(meetsContainingNumberCriteria(s)) metCounts++; //숫자포함
//		if(meetsContainingUppercaseCriteria(s)) metCounts++; //대문자포함
		
//		boolean lengthEnough = s.length() >= 8; //길이
//		if(lengthEnough) metCounts++;
//		boolean containsNum  = meetsContainingNumberCriteria(s); //숫자포함
//		if(containsNum) metCounts++;
//		boolean containsUpp   = meetsContainingUppercaseCriteria(s); //대문자포함
//		if(containsUpp) metCounts++;
	
//		if(lengthEnough && !containsNum && !containsUpp) return PasswordStrength.WEAK;
//		if(!lengthEnough && containsNum && !containsUpp) return PasswordStrength.WEAK;
//		if(!lengthEnough && !containsNum && containsUpp) return PasswordStrength.WEAK;
		if(metCounts <= 1) return PasswordStrength.WEAK;
		
//		if(!lengthEnough) return PasswordStrength.NORMAL;
//		if(!containsNum)  return PasswordStrength.NORMAL; 
//		if(!containsUpp)   return PasswordStrength.NORMAL;
		if(metCounts == 2) return PasswordStrength.NORMAL;
		
		return PasswordStrength.STRONG;
	}
	
	private int getMetCriteriaCounts(String s) {
		int metCounts = 0;
		if(s.length() >= 8) metCounts++; //길이
		if(meetsContainingNumberCriteria(s)) metCounts++; //숫자포함
		if(meetsContainingUppercaseCriteria(s)) metCounts++; //대문자포함
		return metCounts;
	}
	private boolean meetsContainingNumberCriteria(String s) {
		for(char ch : s.toCharArray()) {
			if(ch >= '0' && ch<='9') return true;
		}
		return false;
	}
	
	private boolean meetsContainingUppercaseCriteria(String s) {
		for(char ch : s.toCharArray()) {
			if(Character.isUpperCase(ch)) return true;
		}
		return false;
	}
}
