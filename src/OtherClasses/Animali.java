package OtherClasses;

import static OtherClasses.GlobalVar.RgbA;
import static OtherClasses.GlobalVar.RgbB;
import static OtherClasses.GlobalVar.RgbC;
import java.awt.Color;
import java.util.Random;

public class Animali {    
    public String Tipo;
    public Color CellColor = null;   
    public int Vita = 0;
    private int minmov = 0;
    private static int maxmove = 3; // numero di movimenti da fare prima di potersi riprodurre(da considerarne 1 in meno)
    private boolean CanMove = false;

    public Animali(char a_char){
        this.Vita = 10;
        this.minmov = maxmove;
        switch (a_char)   {
            case 'A':
                this.CellColor = RgbA;
                this.Tipo = "A";
                break;
            case 'B':
                this.CellColor = RgbB;
                this.Vita = 20;
                this.minmov = maxmove - 2;
                this.Tipo = "B";
                break;
            case 'C':    
                this.CellColor = RgbC;
                this.Tipo = "C";
                break;
            default:
                this.Vita = 0;
                this.Tipo = "*";
                this.CellColor = Color.WHITE;
        }
    }
    //Spostamento Animali
    
    public void Sposta (Animali[][] Tabella){
        // Spostamento a random
        Random rand = new Random();
        int AddCol = 0;
        int AddRow = 0;        
        this.CheckSposta(Tabella);
        for (int i = 0; i < Tabella.length; i++){
            for (int ii = 0; ii < Tabella[0].length; ii++){
                if (Tabella[i][ii].CanMove == true){
                    Tabella[i][ii].CanMove = false;
                    do {
                        AddCol = rand.nextInt((1 - (-1)) + 1) + (-1);
                        AddRow = rand.nextInt((1 - (-1)) + 1) + (-1);
                    } while ((AddCol == 0 & AddRow == 0));
                    if (!(i + AddRow < 0 || i + AddRow >= Tabella.length || ii + AddCol < 0 || ii + AddCol >= Tabella[0].length)){
                        boolean sposta = false;
                        if (Tabella[i + AddRow][ii + AddCol].RitornaTipo(Tabella[i + AddRow][ii + AddCol].Tipo) == "C"){
                            Tabella[i][ii] = Tabella[i][ii].Alimenta(Tabella[i][ii]);
                            sposta = true;
                        }
                        if (Tabella[i + AddRow][ii + AddCol].RitornaTipo(Tabella[i + AddRow][ii + AddCol].Tipo) == " ")
                            sposta = true;
                        if (sposta == true){
                            Tabella[i + AddRow][ii + AddCol] = Tabella[i][ii];
                            Tabella[i + AddRow][ii + AddCol] = Tabella[i + AddRow][ii + AddCol].SottraiVita(Tabella[i + AddRow][ii + AddCol]);
                            Tabella[i][ii] = new Animali('*');                        
                        }
                    }
                }
            
            }
        
        }
        
        //Tabella = this.Riproduci(Tabella);
    }
    private void CheckSposta (Animali[][] CheckArray){
        for (int i = 0; i < CheckArray.length; i++){
            for (int ii = 0; ii < CheckArray[0].length; ii++){
                if (CheckArray[i][ii].RitornaTipo(CheckArray[i][ii].Tipo) == "A" || CheckArray[i][ii].RitornaTipo(CheckArray[i][ii].Tipo) == "B")
                    CheckArray[i][ii].CanMove = true;
            }
        }
    }
    // Accoppia/Clona    
    public void Riproduci(Animali[][] WKTabella){
        for (int i = 0; i < WKTabella.length; i++){
            for (int ii = 0; ii < WKTabella[0].length; ii++){
                if (WKTabella[i][ii].Tipo == "A") // Accoppia
                    this.AccoppiaTipoA(WKTabella, i, ii);
                else
                    if (WKTabella[i][ii].Tipo == "B")
                        this.ClonaTipoB(WKTabella, i, ii);
            }        
        }
    }
    //Accoppiamento
    private void AccoppiaTipoA (Animali[][] TabAccoppia, int Row, int Col){
        if (CheckAnimaleA(TabAccoppia[Row][Col]))
            return;        
        boolean Prosegui = false;
        int addrow = 0;
        int addcol = 0;
        for (addrow = -1; addrow <=1; addrow++){ // Cerco una specie compatibile vicino
            if (!(Row + addrow < 0 || Row + addrow >= TabAccoppia.length))
                for (addcol = -1; addcol <= 1; addcol++){
                    if (!(Col + addcol < 0 || Col + addcol >= TabAccoppia[0].length))
                        if (CheckAnimaleA(TabAccoppia[Row + addrow][Col + addcol]))
                            Prosegui = true;
                    if (Prosegui == true)
                        break;
                }   
            if (Prosegui == true)
                break;
        }
        if (Prosegui == true){
            Prosegui = false;
            if (Row % 2 == 0)
                for (int rigavuota = -1; rigavuota <= 1; rigavuota++){
                    if (!(Row + rigavuota < 0 || Row + rigavuota >= TabAccoppia.length ))
                        for (int colvuota = -1; colvuota <= 1; colvuota++){
                            if (!(Col + colvuota < 0 || Col + colvuota >= TabAccoppia[0].length))
                                if (RitornaTipo(TabAccoppia[Row + rigavuota][Col + colvuota].Tipo) == " "){
                                    TabAccoppia[Row + rigavuota][Col + colvuota] = new Animali('A');
                                    Prosegui = true;
                                }
                            if (Prosegui == true)
                                break;
                        }
                    if (Prosegui == true)
                        break;                        
                }
            else
                for (int rigavuota = 1; rigavuota >= -1; rigavuota--){
                    if (!(Row + rigavuota < 0 || Row + rigavuota >= TabAccoppia.length ))
                        for (int colvuota = 1; colvuota >= -1; colvuota--){
                            if (!(Col + colvuota < 0 || Col + colvuota >= TabAccoppia[0].length))
                                if (RitornaTipo(TabAccoppia[Row + rigavuota][Col + colvuota].Tipo) == " "){
                                    TabAccoppia[Row + rigavuota][Col + colvuota] = new Animali('A');
                                    Prosegui = true;
                                }
                            if (Prosegui == true)
                                break;
                        }
                    if (Prosegui == true)
                        break;                        
                }                
        }
        if (Prosegui == true){
            TabAccoppia[Row][Col].minmov = maxmove;
            TabAccoppia[Row + addrow][Col + addcol].minmov = maxmove;                        
        }                        
    }    

    private boolean CheckAnimaleA(Animali animal){
        if (!(animal.Tipo == "A"))
            return false;
        if ((animal.minmov <= 0 & animal.Vita > 5 & animal.Vita < 20))
            return true;
        return false;
    }
    //Clona
    private void ClonaTipoB (Animali[][] TabClona, int Row, int Col){
        if (!(CheckAnimaleB(TabClona[Row][Col]) == true))
            return;
        boolean Prosegui = false;
        if (!(Row % 2 == 0))
            for (int rigavuota = -1; rigavuota <= 1; rigavuota++){
                if (!(Row + rigavuota < 0 || Row + rigavuota >= TabClona.length ))
                    for (int colvuota = -1; colvuota <= 1; colvuota++){
                        if (!(Col + colvuota < 0 || Col + colvuota >= TabClona[0].length))
                            if ((RitornaTipo(TabClona[Row + rigavuota][Col + colvuota].Tipo) == " ") & (TabClona[Row + rigavuota][Col + colvuota].CanMove == true)){
                                TabClona[Row + rigavuota][Col + colvuota] = new Animali('B');
                                TabClona[Row + rigavuota][Col + colvuota].CanMove = false;
                                TabClona[Row][Col].Vita = 10;
                                TabClona[Row][Col].minmov = TabClona[Row][Col].maxmove - 2;
                                Prosegui = true;
                            }
                        if (Prosegui == true)
                            break;
                    }
                if (Prosegui == true)
                    break;                        
            }
        else
            for (int rigavuota = 1; rigavuota >= -1; rigavuota--){
                if (!(Row + rigavuota < 0 || Row + rigavuota >= TabClona.length ))
                    for (int colvuota = 1; colvuota >= -1; colvuota--){
                        if (!(Col + colvuota < 0 || Col + colvuota >= TabClona[0].length))
                            if (RitornaTipo(TabClona[Row + rigavuota][Col + colvuota].Tipo) == " "){
                                TabClona[Row + rigavuota][Col + colvuota] = new Animali('B');
                                TabClona[Row + rigavuota][Col + colvuota].CanMove = false;
                                TabClona[Row][Col].Vita = 10;
                                TabClona[Row][Col].minmov = TabClona[Row][Col].maxmove - 2;
                                Prosegui = true;
                            }
                        if (Prosegui == true)
                            break;
                    }
                if (Prosegui == true)
                    break;                        
            }                            
    }
    private boolean CheckAnimaleB(Animali animal){
        if (animal.Tipo != "B" || animal.minmov > 0)
            return false;
        if (animal.Vita >= 20)
            return true;
        return false;
    }        
    public String RitornaTipo(String t){
        if (t == "*")
            return " ";
        return t;
    }
    private Animali Alimenta(Animali an){
        an.Vita = an.Vita + 2;
        if (an.Tipo == "B")
            an.Vita = an.Vita + 2;
        return an;
    }
    private Animali SottraiVita(Animali an){
        an.Vita--;
        if (an.Tipo == "B")
            an.Vita--;
        if (an.Vita <= 0)
            return new Animali('*');
        an.minmov--;
        return an;
    }
}
