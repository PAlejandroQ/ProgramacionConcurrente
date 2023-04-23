package redesOK;





import java.util.Scanner;
import redesOK.TCPClient50;

class Cliente50{
    TCPClient50 mTcpClient;
    Scanner sc;
    public static void main(String[] args)  {
        Cliente50 objcli = new Cliente50();
        objcli.iniciar();
    }
    void iniciar(){
       new Thread(
            new Runnable() {

                @Override
                public void run() {
//                    mTcpClient = new TCPClient50("10.10.0.191",
                    mTcpClient = new TCPClient50("10.10.0.197",
                        new TCPClient50.OnMessageReceived(){
                            @Override
                            public void messageReceived(String message){
                                ClienteRecibe(message);
                            }
                        }
                    );
                    mTcpClient.run();                   
                }
            }
        ).start();
        //---------------------------
       
        String salir = "n";
        sc = new Scanner(System.in);
        System.out.println("Cliente bandera 01");
        while( !salir.equals("s")){
            salir = sc.nextLine();

            ClienteEnvia(salir);
        }
        System.out.println("Cliente bandera 02");
    
    }
    void ClienteRecibe(String llego){
        System.out.println("CLINTE50 El mensaje::" + llego);
        if (llego.equals("envia 123")){
            int tem=0;
            for(int i=0;i<200;i++){
                tem+=i;
            }
            ClienteEnvia(Integer.toString(tem));
        }
    }
    void ClienteEnvia(String envia){
        if (mTcpClient != null) {

            mTcpClient.sendMessage(envia);
        }
    }

}
