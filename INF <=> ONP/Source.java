//Maryia Babinskaya 1
import java.util.Scanner;
public class Source {
    public static Scanner in = new Scanner(System.in);

    static class Link{
        public char znak;
        public String str;
        public Link next;

        public Link(char d) { znak = d; }
        public Link(String  d) { str =d ; }
    }
    static class LinkStack{
        public Link top;
        public LinkStack() { top = null; }
        //Wstawianie na poczatek
        public void pushStr(String d){
            Link newLink = new Link(d);
            newLink.next = top;
            top = newLink;
        }
        public void push(char d){
            Link newLink = new Link(d);
            newLink.next = top;
            top = newLink;
        }
        public boolean isEmpty() { return (top==null); }
        //usuwa element ze szczytu stosa
        public char pop(){
            Link temp = top;
            top = top.next;
            return temp.znak;
        }
        public String popStr(){
            Link temp = top;
            top = top.next;
            return temp.str;
        }
    }

    public static boolean checkINF(String str){ //sprawdzamy czy dobry INF
        String str1 = str.replaceAll("[()]",""); //wyrzucamy nawiasy
        int counter = 0; // sprawdzamy zeby nie bylo za duzo operatorow
        for(int i = 0; i < (str1.length()-1); i++){
            //sprawdzamy czy nie idzie 2 operatora albo operanda jeden za jednym
            if(str1.charAt(i)>='a'&& str1.charAt(i)<='z'
                    && str1.charAt(i+1)>='a'&& str1.charAt(i+1)<='z')
                return false;
            if((str1.charAt(i)<'a'|| str1.charAt(i)>'z')
                    && ((str1.charAt(i+1)<'a'|| str1.charAt(i+1)>'z')&& (str1.charAt(i+1)!='~' && str1.charAt(i+1)!='!')))
                return false;

            if(counter < 0) return false; // sprawdzamy zeby nie bylo za duzo operatorow lub operandow
            if(str1.charAt(i)>='a'&& str1.charAt(i)<='z')
                counter++;
            else if(str1.charAt(i)!='~' && str1.charAt(i)!='!')
                counter--;
        }
        if(priorytet(str.charAt(str.length()-1))!=(9)){ //jezeli ostatni element jakis znaczek
            return false; }
        //sprawdzenie '(',')'
        int proverka = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i)=='(')
                proverka++;
            else if(str.charAt(i)==')')
                proverka--;
            if(proverka < 0)
                return false;  // jezeli za duzo jakiegos nawiasa
        }
        return counter==0 && proverka==0 && !str.contains("()");
    }

    public static boolean checkONP(String str){ //sprawdzamy czy dobry ONP
        if((str.charAt(0)<'a'||str.charAt(0)>'z') ||
                ( str.length()!=1 && (str.charAt(1)<'a'||str.charAt(1)>'z') && ( str.charAt(1)!='~' && str.charAt(1)!='!')) ||
                (str.length()!=1 && str.charAt(str.length()-1)>='a'&& str.charAt(str.length()-1)<='z'))
            // jezeli (1) 0 element operator, (2) na 1 operator ale nie jest ~ lub !, (3) jezeli ostatni operand
            return false;

        int counter = 0; // sprawdzamy zeby nie bylo za duzo operatorow
        for(int i = 0; i < str.length(); i++){
            if(counter < 0) return false;
            if(str.charAt(i)>='a'&& str.charAt(i)<='z')
                counter++;
            else if(str.charAt(i)!='~' && str.charAt(i)!='!')
                counter--;
        }
        return counter==1;
    }

    static int priorytet(char d){ //priorytet od najmnieszego (po tablice)
        switch(d){
            case '=': return 0;
            case '|': return 1;
            case '&': return 2;
            case '?': return 3;
            case '<': return 4;
            case '>': return 4;
            case '+': return 5;
            case '-': return 5;
            case '*': return 6;
            case '/': return 6;
            case '%': return 6;
            case '^': return 7;
            case '~': return 8;
            case '!': return 8;
        }
        return 9;
    }
    static boolean prawOp(char op){
        switch(op){
            case '=': return true;
            case '^': return true;
            case '~': return true;
            case '!': return true;
        }
        return false;
    }
    static void ONPtoINF(String str){
        LinkStack stack = new LinkStack();
        int Priorytet =0;
        String nasz_result;
        String tmp = "";
        for(int j = 0; j < str.length(); j++) {
            if(str.charAt(j) >= 'a' && str.charAt(j) <= 'z'){ //operandy
                stack.pushStr(str.charAt(j)+"");           //wstawiamy na poczatek naszej listy(stos)
                Priorytet = Priorytet*10 + 9;
            }
            else {
                if(!prawOp(str.charAt(j))) { //jezeli nie prawostronny, patrzymy na priorytet i wstawiamy ()
                    if(Priorytet%10 <= priorytet(str.charAt(j)))
                        tmp = "(" + stack.popStr() + ")";
                    else
                        tmp = stack.popStr();

                    Priorytet/=10;

                    if((Priorytet%10 < priorytet(str.charAt(j))))
                        tmp = "(" + stack.popStr() + ")" + str.charAt(j) + tmp;
                    else
                        tmp = stack.popStr() + str.charAt(j) + tmp;

                    Priorytet/=10;
                }
                //rozpatrzymy dla prawostronnych =!~^
                else if(str.charAt(j)=='='){
                    tmp = stack.popStr();
                    Priorytet/=10;
                    tmp = stack.popStr() + str.charAt(j) + tmp;
                    Priorytet/=10;
                }
                else if (str.charAt(j)=='~' || str.charAt(j)=='!' ) {
                    if(Priorytet%10 < priorytet(str.charAt(j)))
                        tmp = str.charAt(j) + "(" + stack.popStr() + ")";
                    else
                        tmp = str.charAt(j) + stack.popStr();
                    Priorytet/=10;
                }
                else {
                    if(Priorytet%10 < priorytet(str.charAt(j)))
                        tmp = "(" + stack.popStr() + ")";
                    else
                        tmp = stack.popStr();
                    Priorytet/=10;
                    if((Priorytet%10 <= priorytet(str.charAt(j))))
                        tmp = "(" + stack.popStr() + ")" + str.charAt(j) + tmp;
                    else
                        tmp = stack.popStr() + str.charAt(j) + tmp;

                    Priorytet/=10;
                }
                stack.pushStr(tmp);
                Priorytet = Priorytet*10 + priorytet(str.charAt(j));
            }
        }

        nasz_result = stack.popStr();
        char[] strToArray = nasz_result.toCharArray();
        System.out.print("INF: ");
        for(int q = 0; q < strToArray.length; q++)
            System.out.print(strToArray[q] + " ");
        System.out.println("");
    }


    static public String INFtoONP( String str){
        String res = new String();
        LinkStack stack = new LinkStack();
        for(int i = 0; i < str.length(); i++){
            char t = str.charAt(i);
            if(t>='a'&& t<='z'){ //t - operand
                res+=t;          //odrazu dodajemy do resultu
            }
            else if(t=='('){
                stack.push(t);
            }
            else if(t == ')'){
                while( ! stack.isEmpty() ) { //dopoki nie pusty
                    char chx = stack.pop();  // usuwamy z listy
                    if( chx == '(' )
                        break;
                    else
                        res = res + chx;  //dopisujemy w result
                }
            }
            else if(!prawOp(t)){  //jezeli nie prawostronny
                while(!stack.isEmpty()){ //dopoki nie pusty
                    char t1 = stack.pop();
                    if(t1 == '('){
                        stack.push(t1);
                        break;
                    }
                    else {
                        if( priorytet(t1)<priorytet(t)){
                            stack.push(t1);
                            break;
                        }
                        else{
                            res = res + t1;
                        }
                    }
                }
                stack.push(t);
            }
            else{
                while(!stack.isEmpty()){ //dopoki nie pusty
                    char t1 = stack.pop();
                    if(t1 == '('){
                        stack.push(t1);
                        break;
                    }
                    else {
                        if( priorytet(t1)<=priorytet(t)){
                            stack.push(t1);
                            break;
                        }
                        else{
                            res = res + t1;
                        }
                    }
                }
                stack.push(t);
            }
        }
        while(!stack.isEmpty()){
            res+=stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        int liczba_zestawow;
        String result;
        liczba_zestawow = in.nextInt();
        String INF_ONP = in.nextLine();
        for(int i = 0; i < liczba_zestawow; i++){
            INF_ONP = (in.nextLine());
            if(INF_ONP.startsWith("INF: ")){
                String str = INF_ONP.replaceAll("[^a-z+=<>()*/%~!?&|^-]", ""); //zamieniamy na ""
                if(checkINF(str)){
                    result = INFtoONP(str);
                    char[] strToArray = result.toCharArray();
                    System.out.print("ONP: ");
                    for(int q = 0; q < strToArray.length; q++)
                        System.out.print(strToArray[q] + " ");
                    if(i != liczba_zestawow) System.out.println("");
                }
                else
                  System.out.println("ONP: error");
            }
            else if(INF_ONP.startsWith("ONP: ")){
                String str = INF_ONP.replaceAll("[^a-z+=<>*/!?&|%~^-]", "");
                if(checkONP(str)){
                    ONPtoINF(str);
                }
                else
                    System.out.println("INF: error");
            }
        }
    }
}
//10
//INF: a^b*c-d<xp|q+x
//INF: x=~a*b/c-d+e%~f
//INF: x=a^b^c
//INF: ((a+b*c))
//INF: x=a=b=c^d^e
//ONP: a
//ONP: ab*cd**
//ONP: ab+a~a-+
//ONP: ab+cd++
//ONP: ab/c*
// Wyjscie
//ONP: error
//ONP: x a ~ b * c / d - e f ~ % + =
//ONP: x a b c ^ ^ =
//ONP: a b c * +
//ONP: x a b c d e ^ ^ = = =
//INF: a
//INF: a * b * ( c * d )
//INF: a + b + ( ~ a - a )
//INF: a + b + ( c + d )
//INF: a / b * c