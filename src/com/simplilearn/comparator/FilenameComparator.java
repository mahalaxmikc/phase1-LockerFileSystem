package com.simplilearn.comparator;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.regex.Pattern;

public final class FilenameComparator implements Comparator<String> {
	
	 private static final Pattern INTEGERS = 
		        Pattern.compile("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

	@Override
	public int compare(String string1, String string2) {
        if (string1 == null || string2 == null)
            return string1 == null ? string2 == null ? 0 : -1 : 1;
 
        String[] split1 = INTEGERS.split(string1);
        String[] split2 = INTEGERS.split(string2);
        int length = Math.min(split1.length, split2.length);
 
      
        for (int i = 0; i < length; i++) {
            char char1 = split1[i].charAt(0);
            char char2 = split2[i].charAt(0);
            int cmp = 0;
 
            if (char1 >= '0' && char1 <= '9' && char2 >= '0' && char2 <= '9')
                cmp = new BigInteger(split1[i]).compareTo(
                      new BigInteger(split2[i]));
 
            if (cmp == 0)
                cmp = split1[i].compareTo(split2[i]);
            if (cmp != 0)
                return cmp;
        }
 
        return split1.length - split2.length;
	}

}
