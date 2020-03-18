package com.training.coronavirus;


import java.util.ArrayList;
import java.util.Arrays;

public class Record {


    public Record() {
    }

    ArrayList<String> Lessof30percent = new ArrayList(Arrays.asList(" اعثر على شخص يبادلك الشعور بالإعجاب",
            "إذا كان الطرف الآخر لا يبادلك نفس المشاعر فإن هذا يجعلك تشكك في نفسك وفي قدراتك عند أبسط المواقف، كما يقلل من ثقتك بنفسك ",
            "أحياناً عندما تفشل العلاقة، عليك الاعتراف بذلك وتقبله، خاصة عندما يتحول ”حب حياتك“ لشخص أناني ولئيم فأقنع نفسك أنك تستحق الأفضل"
            , "تجنب المواقف التي قد يتوقع فيها شريكك أمور أكثر مما أنت مستعد على منحه إياه"));


    ArrayList<String> Between30And50 = new ArrayList(Arrays.asList("امنح الحب الوقت الكافي ليكبر بينكما ",
            "يمكن أن يرتقي بك الحب لأعلى المشاعر والأحاسيس الجميلة أو يهوي بك الى أكثر الحالات حزناً وكآبة وتوتراً ",
            "كن حذرا في العلاقةً فالعقل مهمً لا تقف فقط على المشاعر "
            , "أكثر من الملاحظة في العلاقة فستكتشف عدة أشياء يجب إصلاحها"));

    ArrayList<String> Between50And80 = new ArrayList(Arrays.asList("علاقتك مع شريكك ليست سيئة لكنها تستحق مجهود نفسي أكبر",
            "الحب ليس فقط أجواء ممتعة بل كفاح عندما يتطلب ذلك",
            "ضع حداً للأشياء التي قد تشعرك بالقلق وسط العلاقة"
            , "لا تخشى من صراحة شريكك إذا توجب توضيح بعض الاشياء  "));


    ArrayList<String> Between80And100 = new ArrayList(Arrays.asList("علاقتك بشريكك جميلة جداً فحافظ عليها",
            "الحب فرصة ليصبح الإنسان أفضل وأجمل وأرقى",
            "أنت محظوظ في الحب فأسعى كي تكون موجوداً في أصعب الظروف مع شريكك "
            , "حافظوا على الصراحة بينكما فهي سر النجاح في العلاقة "));


    public ArrayList<String> getLessof30percent() {
        return Lessof30percent;
    }


    public ArrayList<String> getBetween30And50() {
        return Between30And50;
    }


    public ArrayList<String> getBetween50And80() {
        return Between50And80;
    }


    public ArrayList<String> getBetween80And100() {
        return Between80And100;
    }


}
