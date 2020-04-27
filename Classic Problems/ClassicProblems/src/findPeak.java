public class findPeak {
    public int findpeak(int[] num){
        int first = 0;
        int sec = 0;
        int thrd = 0;
        if(num.length>2){
            for(int i = 0; i<num.length-2;i++) {
                first = num[i]; sec = num[i+1]; thrd = num[i+2];
                if(sec>first&&sec>thrd)
                    return sec;
            }
        }else if(num.length<=2){
            if(num.length==1){
                return num[0];
            }
            else{
                return Math.max(num[0],num[1]);
            }

        }
        return 0;


    }
}
