package com.env.threadpool;

import java.util.Date;

/**
 * Created by alex on 5/22/15.
 */
public class CommonQueue<T> {

    private class QueueElement<T>{
        private T element;
        private QueueElement<T>  next;
        private Date creationDate = new Date();

        public  QueueElement(T object){
            this.element = object;
        }

        public Date getCreationDate(){
            return creationDate;
        }

        public T  getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public QueueElement<T>  getNext() {
            return next;
        }

        public void setNext(QueueElement<T> next) {
            this.next = next;
        }


    }

    private QueueElement<T> head = null;
    private QueueElement<T> tail = null;


    public boolean needMoreThreads(){
        if (head == null){
            return false;
        }else {
        return new Date().getTime() - head.getCreationDate().getTime() > 2000;
        }
    }


    public synchronized T pull(){
        if (head == null){
            return null;
        }else{
            QueueElement<T>  temp = head;
            head = head.getNext();
            return temp.getElement();
        }
    }

    public synchronized void push(T object){
        if (object == null){
            return;
        }

        QueueElement<T> element = new QueueElement(object);

        if (head == null ){
            head = element;
            tail = element;
        }else{
            tail.setNext(element);
            tail= element;
        }
        this.notify();
    }

    public synchronized boolean isEmpty(){
        return head == null;
    }
}
