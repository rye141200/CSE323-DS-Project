package com.example.demo.SNA_Undo;

public class PairObject<K,V> {
    private K line;
    private V lineIndex;
    public PairObject(K line,V lineIndex){
        this.line = line;
        this.lineIndex = lineIndex;
    }
    public K getKey(){
        return line;
    }
    public V getValue(){
        return lineIndex;
    }
    public void setValue(V value){
        this.lineIndex = value;
    }
    public boolean isEqual(PairObject<K,V> otherObject){
        return (otherObject.getKey() == this.line) && (otherObject.getValue() == this.lineIndex);
    }
    @Override
    public String toString(){
        return  line  + " "+ lineIndex+"\n";
    }
}
