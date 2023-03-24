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

%init{
    yyline = 0;
    yychar= 0;
%init}

//simbolos
GUION = "-"
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

ESC = "\\n" | "\\\"" | "\\\'"
NO_ESC = [^\'\"]

//expresiones regulares
NUM = [0-9]+
DECIMAL = [0-9]+("."[ |0-9]+)?
ESPACIO = [\ \t\r\n]+
LINEAS = [^!]
COMENT_ONELINE = "//".*
COMENT_MORLINE = "<!"{LINEAS}* "!>"
CHAR_MAY = [A-Z]
CHAR_MIN = [a-z]
IDENTIFICADOR = [a-zA-Z_] [a-zA-Z0-9_]+
CARACTER = (\" {NO_ESC} \") | {ESC} 
CADENA = \" ([^\"] | "\\\"")+ \"
ASCII = [!-\/:-@\[-`{-}]




%%

<YYINITIAL> {CONJUNTO} {   return new Symbol(sym.CONJUNTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {COMENT_ONELINE} {   }

<YYINITIAL> {COMENT_MORLINE} {   }

<YYINITIAL> {COR_IZQ} {  return new Symbol(sym.COR_IZQ, yyline, yycolumn, yytext()); }

<YYINITIAL> {COR_DER} {  return new Symbol(sym.COR_DER, yyline, yycolumn, yytext()); }

<YYINITIAL> {GUION} {  return new Symbol(sym.GUION, yyline, yycolumn, yytext()); }

<YYINITIAL> {MAYOR} {  return new Symbol(sym.MAYOR, yyline, yycolumn, yytext()); }

<YYINITIAL> {COMA} {  return new Symbol(sym.COMA, yyline, yycolumn, yytext()); }

<YYINITIAL> {CONC} {  return new Symbol(sym.CONC, yyline, yycolumn, yytext()); }

<YYINITIAL> {DOS_PUNTOS} {  return new Symbol(sym.DOS_PUNTOS, yyline, yycolumn, yytext()); }

<YYINITIAL> {PORC} {   return new Symbol(sym.PORC, yyline, yycolumn, yytext()); }

<YYINITIAL> {PUNTOCOMA} {  return new Symbol(sym.PUNTOCOMA, yyline, yycolumn, yytext()); }

<YYINITIAL> {EQUI} {  return new Symbol(sym.EQUI, yyline, yycolumn, yytext()); }

<YYINITIAL> {DIS} {   return new Symbol(sym.DIS, yyline, yycolumn, yytext()); }

<YYINITIAL> {MAS} {  return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }

<YYINITIAL> {ASTERISCO} {  return new Symbol(sym.ASTERISCO, yyline, yycolumn, yytext()); }

<YYINITIAL> {INTERROGACION} {   return new Symbol(sym.INTERROGACION, yyline, yycolumn, yytext()); }

<YYINITIAL> {COM_SIMPLE} {  return new Symbol(sym.COM_SIMPLE, yyline, yycolumn, yytext()); }

<YYINITIAL> {COM_DOBLE} {}

<YYINITIAL> {NUM} {  return new Symbol(sym.NUM, yyline, yycolumn, yytext()); }

<YYINITIAL> {DECIMAL} {   return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext()); }

<YYINITIAL> {ASCII} {   return new Symbol(sym.ASCII, yyline, yycolumn, yytext()); }

<YYINITIAL> {CHAR_MAY} {  return new Symbol(sym.CHAR_MAY, yyline, yycolumn, yytext());}

<YYINITIAL> {CHAR_MIN} {  return new Symbol(sym.CHAR_MIN, yyline, yycolumn, yytext());}

<YYINITIAL> {IDENTIFICADOR} {  return new Symbol(sym.IDENTIFICADOR, yyline, yycolumn, yytext()); }

<YYINITIAL> {CARACTER} {  return new Symbol(sym.CARACTER, yyline, yycolumn, yytext()); }

<YYINITIAL> {CADENA} {  return new Symbol(sym.CADENA, yyline, yycolumn, yytext()); }

<YYINITIAL> {ESPACIO} {}

<YYINITIAL> . {
    String err = "Error Lexico  : '"+yytext()+"' en la linea: "+(yyline+1)+"y columna: "+(yycolumn+1);
    System.out.println(err);

}
