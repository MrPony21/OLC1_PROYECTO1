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
//MAYOR = ">"
//MENOR = "<"
DOS_PUNTOS = ":"
DIAG = "/"
PORC = "%"
COR_IZQ = "{"
COR_DER = "}"
PUNTOCOMA = ";"
EQUI = "~"
CONC = "."
DIS =  "|"
ASTER = "*"
MAS = "+"
INTERROGACION = "?"
COM_SIMPLE = "'"
COM_DOBLE = "\""

//palabras reservadas
CONJUNTO = "CONJ"
//TDL = "tdl"

//expresiones regulares
SALTO = [\ \n]
NUM = [0-9]+
DECIMAL = [0-9]+("."[ |0-9]+)?
ESPACIO = [\ \r\t\f\t]
COMENT_ONELINE = [\/\/.*]
COMENT_MORLINE = ["<!"(.*)"!>"]
IDENTIFICADOR = [A-za-zñÑ0-9]+
ASCII = [!-/:-@\[-`{-}]



%%
<YYINITIAL> {COMENT_ONELINE} { System.out.println(yytext()); return new Symbol(sym.COMENT_ONELINE, yyline, yycolumn, yytext()); }

<YYINITIAL> {COMENT_MORLINE} { System.out.println(yytext()); return new Symbol(sym.COMENT_MORLINE, yyline, yycolumn, yytext()); }

<YYINITIAL> {ESPACIO} {}

<YYINITIAL> {CONJUNTO} { System.out.println(yytext());  return new Symbol(sym.CONJUNTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {GUION} { System.out.println(yytext()); return new Symbol(sym.GUION, yyline, yycolumn, yytext()); }

<YYINITIAL> {GUION_BAJO} { System.out.println(yytext()); return new Symbol(sym.GUION_BAJO, yyline, yycolumn, yytext()); }

<YYINITIAL> {MAYOR} { System.out.println(yytext()); return new Symbol(sym.MAYOR, yyline, yycolumn, yytext()); }

<YYINITIAL> {MENOR} { System.out.println(yytext()); return new Symbol(sym.MENOR, yyline, yycolumn, yytext()); }

<YYINITIAL> {DOS_PUNTOS} { System.out.println(yytext()); return new Symbol(sym.DOS_PUNTOS, yyline, yycolumn, yytext()); }

<YYINITIAL> {DIAG} { System.out.println(yytext()); return new Symbol(sym.DIAG, yyline, yycolumn, yytext()); }

<YYINITIAL> {PORC} {  System.out.println(yytext()); return new Symbol(sym.PORC, yyline, yycolumn, yytext()); }

<YYINITIAL> {COR_IZQ} { System.out.println(yytext()); return new Symbol(sym.COR_IZQ, yyline, yycolumn, yytext()); }

<YYINITIAL> {COR_DER} { System.out.println(yytext()); return new Symbol(sym.COR_DER, yyline, yycolumn, yytext()); }

<YYINITIAL> {PUNTOCOMA} { System.out.println(yytext()); return new Symbol(sym.PUNTOCOMA, yyline, yycolumn, yytext()); }

<YYINITIAL> {EQUI} { System.out.println(yytext()); return new Symbol(sym.EQUI, yyline, yycolumn, yytext()); }

<YYINITIAL> {CONC} { System.out.println(yytext()); return new Symbol(sym.CONC, yyline, yycolumn, yytext()); }

<YYINITIAL> {DIS} {  System.out.println(yytext()); return new Symbol(sym.DIS, yyline, yycolumn, yytext()); }

<YYINITIAL> {ASTER} { System.out.println(yytext()); return new Symbol(sym.ASTER, yyline, yycolumn, yytext()); }

<YYINITIAL> {MAS} { System.out.println(yytext()); return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }

<YYINITIAL> {INTERROGACION} {  System.out.println(yytext()); return new Symbol(sym.INTERROGACION, yyline, yycolumn, yytext()); }

<YYINITIAL> {COM_SIMPLE} { System.out.println(yytext()); return new Symbol(sym.COM_SIMPLE, yyline, yycolumn, yytext()); }

<YYINITIAL> {COM_DOBLE} { System.out.println(yytext()); return new Symbol(sym.COM_DOBLE, yyline, yycolumn, yytext()); }

<YYINITIAL> {SALTO} { }

<YYINITIAL> {NUM} { System.out.println(yytext()); return new Symbol(sym.NUM, yyline, yycolumn, yytext()); }

<YYINITIAL> {DECIMAL} {  System.out.println(yytext()); return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext()); }

<YYINITIAL> {ASCII} {  System.out.println(yytext()); return new Symbol(sym.ASCII, yyline, yycolumn, yytext()); }

<YYINITIAL> {IDENTIFICADOR} { System.out.println(yytext()); return new Symbol(sym.IDENTIFICADOR, yyline, yycolumn, yytext()); }

<YYINITIAL> . {
    String err = "Error Lexico  : '"+yytext()+"' en la linea: "+(yyline+1)+"y columna: "+(yycolumn+1);
    System.out.println(err);

}
