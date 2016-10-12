/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author laurin.agostini
 */
public class ServerController {
    private String key;
    public ServerController(){
        
    }
    
    public void setKey(String key){
        this.key = key;
    }
    
    public String checkKey(String original, String toTest){
        String result = "";
        for(int i = 0; i < original.length(); i++){
            if(original.charAt(i) == toTest.charAt(i)){
                String newMyKey = original.substring(0, i) + 'x' + original.substring(i+1);
                String newToTest = toTest.substring(0, i) + 'x' + toTest.substring(i+1);
                original = newMyKey;
                toTest = newToTest;
                result += 'B';
            }
        }
        for(int i = 0; i < original.length(); i++){
            if(original.charAt(i) != 'x'){
                for(int u = 0; u < toTest.length(); u++){
                    if(original.charAt(i) == toTest.charAt(u)){
                        String newMyKey = original.substring(0, i) + 'x' + original.substring(i+1);
                        String newToTest = toTest.substring(0, u) + 'x' + toTest.substring(u+1);
                        original = newMyKey;
                        toTest = newToTest;
                        result += 'W';
                        break;
                    }
                }
            }
        }
        if(result.length() > 0){
            return result;
        }
        return "0";
    }
    
}
