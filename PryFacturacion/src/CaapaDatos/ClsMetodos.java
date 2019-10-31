/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CaapaDatos;

/**
 *
 * @author pablo
 */
public class ClsMetodos {
    
    private float _subt;
    private float _total;

    public ClsMetodos(float _subt, float _total) {
        this._subt = _subt;
        this._total = _total;
    }

    public float getSubt() {
        return _subt;
    }

    public void setSubt(float _subt) {
        this._subt = _subt;
    }

    public float getTotal() {
        return _total;
    }

    public void setTotal(float _total) {
        this._total = _total;
    }
    
    public float subtotal (int can, float precio){
        _subt = can*precio;
        return _subt;
    }
    
    
}
