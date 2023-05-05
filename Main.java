package com.company;

import java.util.Scanner;

public class Main {

    static class Validador{
        /*  
         *E=TA|T
         * A=+T|+TA
         * T=FB|F
         * B=*F|*FB
         * F=(E)|i
         */

        public String cadena;
        public int cursor;
        public boolean error = false;

        public char caracter(){
            return cursor < cadena.length() ? cadena.charAt(cursor) : 0;
        }

        public boolean validar(String cadena){
            this.cadena = cadena;
            this.cursor = 0;
            E();
            return !error && cursor == cadena.length();
        }

        public void E(){
            T();
            if(error) return;
            int cursorAnterior = cursor;
            A();
            if(error) cursor = cursorAnterior;
            error = false;
        }

        public void A(){
            if (caracter() != '+'){
                error = true;
                return;
            }
            cursor++;
            T();
            if (error) return;
            int cursorAnterior = cursor;
            A();
            if(error) cursor = cursorAnterior;
            error = false;
        }

        public void T(){
            F();
            if(error) return;
            int cursorAnterior = cursor;
            B();
            if(error) cursor = cursorAnterior;
            error = false;
        }

        public void B(){
            if (caracter() != '*'){
                error = true;
                return;
            }
            cursor++;
            F();
            if (error) return;
            int cursor_anterior = cursor;
            B();
            if(error) cursor = cursor_anterior;
            error = false;
        }

        public void F(){
            if (caracter() == '('){
                cursor++;
                E();
                if (caracter() == ')'){
                    cursor++;
                }else{
                    error = true;
                }
            }else if (caracter() == 'i'){
                cursor++;
            }else{
                error = true;
            }
        }
    }

    public static void main(String[] args) {
	// write your code here
        Scanner s = new Scanner(System.in);
        Validador v = new Validador();
        System.out.println(v.validar(s.nextLine()));
    }
}
