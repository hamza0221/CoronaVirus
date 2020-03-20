package com.training.coronavirus;


import java.util.ArrayList;
import java.util.Arrays;

public class Record {


    public Record() {
    }

    ArrayList<String> Lessof50percent = new ArrayList(Arrays.asList("لا تقلق نفسك و عائلتك بالموضوع فعلى الأرجح أنت سليم من الفيروس ",
            "لا تخف لقد تعرضت إلى نزلة برد على ما يبدو",
            "أنت فقط مريض تحتاج إلى الراحة فالامر هو ليس بالخطير ",
            "لا تنسى إتباع اجراءت الوقاية و السلامة اللازمة من الفيروس "
             ));


    ArrayList<String> Between50And80 = new ArrayList(Arrays.asList("قد تكون لديك عوراض الفيروس فاحذر ",
            "قم بالإختبار الطبي إن تعكرت حالتك و كثرت عندك حالة السعال الجاف و الضيق في التنفس ",
            "أكمل الفترة الزمنية اللازمة كي تظهر كل عوارض الفيروس "
            , "اشرب الكثير من الماء،الزيت و الحوامض وإن كنت مدخن فأقلع في الحال !!"));


    ArrayList<String> Between80And100 = new ArrayList(Arrays.asList("أنت حامل لأعراض الفيروس فاعزل نفسك في الحال ",
            "إتصل بالخدمات الطبية الطارئة و أخبرهم فوراً ",
            "لا تلمس أو تقترب من أي شخصٍ فالعدوى سريعة التنقل  "
            , "ثق بالله و توكل عليه و إتبع التعاليم الطبية بحوافرها وانشالله بالشفاء "));


    public ArrayList<String> getLessof50percent() {
        return Lessof50percent;
    }

    public ArrayList<String> getBetween50And80() {
        return Between50And80;
    }

    public ArrayList<String> getBetween80And100() {
        return Between80And100;
    }
}


