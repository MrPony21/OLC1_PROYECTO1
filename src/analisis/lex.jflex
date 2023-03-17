package analisis;
import java_cup.runtime.Symbol;

%%

%{


%}

%class scanner 
%cup
%public
%line
%char
%unicode
%ignorecase

%init{
    yyline = 0;
    yychar= 0;
%init}

//simbolos
GUION = "-"
GUION_BAJO = "_"
DOS_PUNTOS = ":"
PORC = "%"
COR_IZQ = "{"
COR_DER = "}"
PUNTOCOMA = ";"
EQUI = "~"
DIS =  "|"
MAS = "+"
INTERROGACION = "?"
COM_SIMPLE = "'"
COM_DOBLE = "\""
MAYOR = ">"
CONC = "."
ASTERISCO = "*"
COMA = ","

//palabras reservadas
CONJUNTO = "CONJ"
//TDL = "tdl"

ESC = "\\N" | "\\\"" | "\\\'"
NO_ESC = [^\'\"]

//expresiones regulares
SALTO = [\ \n]
NUM = [0-9]+
DECIMAL = [0-9]+("."[ |0-9]+)?
ESPACIO = [\ \r\t\f\t]
LINEAS = [^!]
COMENT_ONELINE = "//".*
COMENT_MORLINE = "<!"{LINEAS}* "!>"
CHAR_MAY = [A-Z]
CHAR_MIN = [a-z]
IDENTIFICADOR = [A-za-zñÑ0-9]+
ESPECIALES = (\" {NO_ESC} \") | {ESC} 
CADENA = \" ([^\"] | "\\\"")+ \"
//ASCII = [!-/:-@\[-`{-}]



%%

<YYINITIAL> {CONJUNTO} { System.out.println(yytext());  return new Symbol(sym.CONJUNTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {COMENT_ONELINE} { System.out.println(yytext());  }

<YYINITIAL> {COMENT_MORLINE} { System.out.println(yytext());  }

<YYINITIAL> {GUION} { System.out.println(yytext()); return new Symbol(sym.GUION, yyline, yycolumn, yytext()); }

<YYINITIAL> {GUION_BAJO} { System.out.println(yytext()); return new Symbol(sym.GUION_BAJO, yyline, yycolumn, yytext()); }

<YYINITIAL> {DOS_PUNTOS} { System.out.println(yytext()); return new Symbol(sym.DOS_PUNTOS, yyline, yycolumn, yytext()); }

<YYINITIAL> {PORC} {  System.out.println(yytext()); return new Symbol(sym.PORC, yyline, yycolumn, yytext()); }

<YYINITIAL> {COR_IZQ} { System.out.println(yytext()); return new Symbol(sym.COR_IZQ, yyline, yycolumn, yytext()); }

<YYINITIAL> {COR_DER} { System.out.println(yytext()); return new Symbol(sym.COR_DER, yyline, yycolumn, yytext()); }

<YYINITIAL> {PUNTOCOMA} { System.out.println(yytext()); return new Symbol(sym.PUNTOCOMA, yyline, yycolumn, yytext()); }

<YYINITIAL> {EQUI} { System.out.println(yytext()); return new Symbol(sym.EQUI, yyline, yycolumn, yytext()); }

<YYINITIAL> {DIS} {  System.out.println(yytext()); return new Symbol(sym.DIS, yyline, yycolumn, yytext()); }

<YYINITIAL> {MAS} { System.out.println(yytext()); return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }

<YYINITIAL> {INTERROGACION} {  System.out.println(yytext()); return new Symbol(sym.INTERROGACION, yyline, yycolumn, yytext()); }

<YYINITIAL> {COM_SIMPLE} { System.out.println(yytext()); return new Symbol(sym.COM_SIMPLE, yyline, yycolumn, yytext()); }

<YYINITIAL> {COM_DOBLE} { System.out.println(yytext()); return new Symbol(sym.COM_DOBLE, yyline, yycolumn, yytext()); }

<YYINITIAL> {SALTO} { }

<YYINITIAL> {NUM} { System.out.println(yytext()); return new Symbol(sym.NUM, yyline, yycolumn, yytext()); }

<YYINITIAL> {DECIMAL} {  System.out.println(yytext()); return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext()); }

//<YYINITIAL> {ASCII} {  System.out.println(yytext()); return new Symbol(sym.ASCII, yyline, yycolumn, yytext()); }

<YYINITIAL> {CHAR_MAY} { System.out.println("el char "+yytext()); return new Symbol(sym.CHAR_MAY, yyline, yycolumn, yytext());}

<YYINITIAL> {CHAR_MIN} { System.out.println("el char "+yytext()); return new Symbol(sym.CHAR_MIN, yyline, yycolumn, yytext());}

<YYINITIAL> {IDENTIFICADOR} { System.out.println(yytext()); return new Symbol(sym.IDENTIFICADOR, yyline, yycolumn, yytext()); }

<YYINITIAL> {ESPECIALES} { System.out.println(yytext()); return new Symbol(sym.ESPECIALES, yyline, yycolumn, yytext()); }

<YYINITIAL> {CADENA} { System.out.println(yytext()); return new Symbol(sym.CADENA, yyline, yycolumn, yytext()); }

<YYINITIAL> {MAYOR} { System.out.println(yytext()); return new Symbol(sym.MAYOR, yyline, yycolumn, yytext()); }

<YYINITIAL> {CONC} { System.out.println(yytext()); return new Symbol(sym.CONC, yyline, yycolumn, yytext()); }

<YYINITIAL> {ASTERISCO} { System.out.println(yytext()); return new Symbol(sym.ASTERISCO, yyline, yycolumn, yytext()); }

<YYINITIAL> {COMA} { System.out.println(yytext()); return new Symbol(sym.COMA, yyline, yycolumn, yytext()); }

<YYINITIAL> {ESPACIO} {}

<YYINITIAL> . {
    String err = "Error Lexico  : '"+yytext()+"' en la linea: "+(yyline+1)+"y columna: "+(yycolumn+1);
    System.out.println(err);

}
