public class GasStation {
    public int canTravel(int[] cost, int[] gas, int fromStation){
        int road = gas.length;
        int sumgas = 0;
        int sumcost = 0;

        for(int i = fromStation; i<road; i++){
            sumcost += cost[i];
            sumgas -= gas[i];
            if(sumcost+sumgas<0) return -1;
        }
        return fromStation;
    }


}
