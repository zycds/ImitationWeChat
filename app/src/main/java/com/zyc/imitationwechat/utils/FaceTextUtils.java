package com.zyc.imitationwechat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FaceTextUtils {


    static class FaceText {
        public String text;
        public FaceText(String face) {
            text = face;
        }
    }

    private static List<FaceText> faceTexts = new ArrayList<>();

    static {
        faceTexts.add(new FaceText("\\face_0"));
        faceTexts.add(new FaceText("\\face_1"));
        faceTexts.add(new FaceText("\\face_2"));
        faceTexts.add(new FaceText("\\face_3"));
        faceTexts.add(new FaceText("\\face_4"));
        faceTexts.add(new FaceText("\\face_5"));
        faceTexts.add(new FaceText("\\face_6"));
        faceTexts.add(new FaceText("\\face_7"));
        faceTexts.add(new FaceText("\\face_8"));
        faceTexts.add(new FaceText("\\face_9"));
        faceTexts.add(new FaceText("\\face_10"));
        faceTexts.add(new FaceText("\\face_11"));
        faceTexts.add(new FaceText("\\face_12"));
        faceTexts.add(new FaceText("\\face_13"));
        faceTexts.add(new FaceText("\\face_14"));
        faceTexts.add(new FaceText("\\face_15"));
        faceTexts.add(new FaceText("\\face_16"));
        faceTexts.add(new FaceText("\\face_17"));
        faceTexts.add(new FaceText("\\face_18"));
        faceTexts.add(new FaceText("\\face_19"));
        faceTexts.add(new FaceText("\\face_20"));
        faceTexts.add(new FaceText("\\face_21"));
        faceTexts.add(new FaceText("\\face_22"));
        faceTexts.add(new FaceText("\\face_23"));
        faceTexts.add(new FaceText("\\face_24"));
        faceTexts.add(new FaceText("\\face_25"));
        faceTexts.add(new FaceText("\\face_26"));
        faceTexts.add(new FaceText("\\face_27"));
        faceTexts.add(new FaceText("\\face_28"));
        faceTexts.add(new FaceText("\\face_29"));
        faceTexts.add(new FaceText("\\face_30"));
        faceTexts.add(new FaceText("\\face_31"));
        faceTexts.add(new FaceText("\\face_32"));
        faceTexts.add(new FaceText("\\face_33"));
        faceTexts.add(new FaceText("\\face_34"));
        faceTexts.add(new FaceText("\\face_35"));
        faceTexts.add(new FaceText("\\face_36"));
        faceTexts.add(new FaceText("\\face_37"));
        faceTexts.add(new FaceText("\\face_38"));
        faceTexts.add(new FaceText("\\face_39"));
        faceTexts.add(new FaceText("\\face_40"));
        faceTexts.add(new FaceText("\\face_41"));
        faceTexts.add(new FaceText("\\face_42"));
        faceTexts.add(new FaceText("\\face_43"));
        faceTexts.add(new FaceText("\\face_44"));
        faceTexts.add(new FaceText("\\face_45"));
        faceTexts.add(new FaceText("\\face_46"));
        faceTexts.add(new FaceText("\\face_47"));
        faceTexts.add(new FaceText("\\face_48"));
        faceTexts.add(new FaceText("\\face_49"));
        faceTexts.add(new FaceText("\\face_50"));
        faceTexts.add(new FaceText("\\face_51"));
        faceTexts.add(new FaceText("\\face_52"));
        faceTexts.add(new FaceText("\\face_53"));
        faceTexts.add(new FaceText("\\face_54"));
        faceTexts.add(new FaceText("\\face_55"));
        faceTexts.add(new FaceText("\\face_56"));
        faceTexts.add(new FaceText("\\face_57"));
        faceTexts.add(new FaceText("\\face_58"));
        faceTexts.add(new FaceText("\\face_59"));
        faceTexts.add(new FaceText("\\face_60"));
        faceTexts.add(new FaceText("\\face_61"));
        faceTexts.add(new FaceText("\\face_62"));
        faceTexts.add(new FaceText("\\face_63"));
        faceTexts.add(new FaceText("\\face_64"));
        faceTexts.add(new FaceText("\\face_65"));
        faceTexts.add(new FaceText("\\face_66"));
        faceTexts.add(new FaceText("\\face_67"));
        faceTexts.add(new FaceText("\\face_68"));
        faceTexts.add(new FaceText("\\face_69"));
        faceTexts.add(new FaceText("\\face_70"));
        faceTexts.add(new FaceText("\\face_71"));
        faceTexts.add(new FaceText("\\face_72"));
        faceTexts.add(new FaceText("\\face_73"));
        faceTexts.add(new FaceText("\\face_74"));
        faceTexts.add(new FaceText("\\face_75"));
        faceTexts.add(new FaceText("\\face_76"));
        faceTexts.add(new FaceText("\\face_77"));
        faceTexts.add(new FaceText("\\face_78"));
        faceTexts.add(new FaceText("\\face_79"));
        faceTexts.add(new FaceText("\\face_80"));
        faceTexts.add(new FaceText("\\face_81"));
        faceTexts.add(new FaceText("\\face_82"));
        faceTexts.add(new FaceText("\\face_83"));
        faceTexts.add(new FaceText("\\face_84"));
        faceTexts.add(new FaceText("\\face_85"));
        faceTexts.add(new FaceText("\\face_86"));
        faceTexts.add(new FaceText("\\face_87"));
        faceTexts.add(new FaceText("\\face_88"));
        faceTexts.add(new FaceText("\\face_89"));
        faceTexts.add(new FaceText("\\face_90"));
        faceTexts.add(new FaceText("\\face_91"));
        faceTexts.add(new FaceText("\\face_92"));
        faceTexts.add(new FaceText("\\face_93"));
        faceTexts.add(new FaceText("\\face_94"));
        faceTexts.add(new FaceText("\\face_95"));
        faceTexts.add(new FaceText("\\face_96"));
        faceTexts.add(new FaceText("\\face_97"));
        faceTexts.add(new FaceText("\\face_98"));
        faceTexts.add(new FaceText("\\face_99"));
        faceTexts.add(new FaceText("\\face_100"));
        faceTexts.add(new FaceText("\\face_101"));
        faceTexts.add(new FaceText("\\face_102"));
        faceTexts.add(new FaceText("\\face_103"));
        faceTexts.add(new FaceText("\\face_104"));
        faceTexts.add(new FaceText("\\face_105"));
        faceTexts.add(new FaceText("\\face_106"));
        faceTexts.add(new FaceText("\\face_107"));
        faceTexts.add(new FaceText("\\face_108"));
        faceTexts.add(new FaceText("\\face_109"));
        faceTexts.add(new FaceText("\\face_110"));
        faceTexts.add(new FaceText("\\emotion_del_normal"));
        faceTexts.add(new FaceText("\\emotion_del_down"));
    }

    public static String parse(String s) {
        for (FaceText faceText : faceTexts) {
            s = s.replace("\\" + faceText.text, faceText.text);
            s = s.replace(faceText.text, "\\" + faceText.text);
        }
        return s;
    }

    /**
     * toSpannableString
     * @return SpannableString
     * @throws
     */
    public static SpannableString toSpannableString(Context context, String text) {
        if (!TextUtils.isEmpty(text)) {
            SpannableString spannableString = new SpannableString(text);
            int start = 0;
            Pattern pattern = Pattern.compile("\\\\face_[0-9]{1,3}", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                String faceText = matcher.group();
                String key = faceText.substring(1);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                        context.getResources().getIdentifier(key, "drawable", context.getPackageName()), options);
                ImageSpan imageSpan = new ImageSpan(context, bitmap);
                int startIndex = text.indexOf(faceText, start);
                int endIndex = startIndex + faceText.length();
                if (startIndex >= 0)
                    spannableString.setSpan(imageSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                start = (endIndex - 1);
            }

            return spannableString;
        } else {
            return new SpannableString("");
        }
    }

    public static SpannableString toSpannableString(Context context, String text, SpannableString spannableString) {

        int start = 0;
        Pattern pattern = Pattern.compile("\\\\ue[a-z0-9]{3}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String faceText = matcher.group();
            String key = faceText.substring(1);
            BitmapFactory.Options options = new BitmapFactory.Options();
//          options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                    .getIdentifier(key, "drawable", context.getPackageName()), options);
            ImageSpan imageSpan = new ImageSpan(context, bitmap);
            int startIndex = text.indexOf(faceText, start);
            int endIndex = startIndex + faceText.length();
            if (startIndex >= 0)
                spannableString.setSpan(imageSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = (endIndex - 1);
        }

        return spannableString;
    }

}
