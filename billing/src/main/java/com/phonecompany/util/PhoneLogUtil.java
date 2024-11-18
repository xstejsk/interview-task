package com.phonecompany.util;

import com.phonecompany.billing.model.PhoneLog;

import java.util.HashMap;
import java.util.Map;

public class PhoneLogUtil {

    private static Map<String, Integer> getPhoneCallFrequency(PhoneLog phoneLog) {
    Map<String, Integer> phoneCallFrequency = new HashMap<>();
    phoneLog.getPhoneCalls().forEach(phoneCall -> {
        String phoneNumber = phoneCall.getRecipientNumber();
        phoneCallFrequency.put(phoneNumber, phoneCallFrequency.getOrDefault(phoneNumber, 0) + 1);
    });

    return phoneCallFrequency;
    }

    public static String getMostFrequentLargestPhoneNumber(PhoneLog phoneLog) {
        Map<String, Integer> frequencyMap = getPhoneCallFrequency(phoneLog);
        String mostFrequent = null;
        int maxFrequency = 0;

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String phoneNumber = entry.getKey();
            int frequency = entry.getValue();

            if (frequency > maxFrequency ||
                    (frequency == maxFrequency && (mostFrequent == null || safeParseLong(phoneNumber) > safeParseLong(mostFrequent)))) {
                mostFrequent = phoneNumber;
                maxFrequency = frequency;
            }
        }

        return mostFrequent;
    }

    public static long safeParseLong(String longString) {
        try {
            return Long.parseLong(longString);
        } catch (NumberFormatException e) {
            return Long.MIN_VALUE;
        }
    }
}
