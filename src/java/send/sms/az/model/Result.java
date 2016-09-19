/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

/**
 *
 * @author rasha_000
 */
public class Result extends BaseModel{
    private String result_item;
    private String result_name;

    public Result(String result_item, String result_name) {
        this.result_item = result_item;
        this.result_name = result_name;
    }

    public Result() {
    }

    
    
    public String getResult_item() {
        return result_item;
    }

    public void setResult_item(String result_item) {
        this.result_item = result_item;
    }

    public String getResult_name() {
        return result_name;
    }

    public void setResult_name(String result_name) {
        this.result_name = result_name;
    }

    @Override
    public String toString() {
        return "Result{" + "result_item=" + result_item + ", result_name=" + result_name + '}';
    }
    
    
}
