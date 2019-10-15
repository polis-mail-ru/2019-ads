package ru.mail.polis.ads;

class MyFIFO {
    int[] fifo = new int[100];
    private int lenElems;
    MyFIFO(){
        lenElems = 0;
    }
    void push(int num){
        fifo[lenElems] = num;
        lenElems++;
    }

    void pop(){
        System.out.println(fifo[0]);
        if (fifo.length - 1 >= 0) System.arraycopy(fifo, 1, fifo, 0, fifo.length - 1);
        lenElems--;
    }

    void clear(){
//        for (int i: fifo) {
//            i = 0;
//        }
        fifo[0] = 0;
        lenElems = 0;
    }

    void front(){
        System.out.println(fifo[0]);
    }

    void size(){
        System.out.println(lenElems);
    }
}
