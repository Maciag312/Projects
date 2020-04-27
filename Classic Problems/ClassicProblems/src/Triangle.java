public class Triangle {
    public int moveThroughTriangle(int[][] Path ){
        int smallest = 0;
        int Position = 0;
        int sum = Path[0][0];
        for(int i = 1; i<Path.length;i++){
            smallest = Path[i][Position];
            if(smallest>Path[i][Position+1]){
                Position++;
                smallest = Path[i][Position];
            }
            sum += smallest;
        }
        return sum;
    }
}

