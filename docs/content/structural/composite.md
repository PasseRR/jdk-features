---
layout: page
title: 组合模式
permalink: composite.html
---

> 将对象组合成树形结构以表示“部分-整体”的层次结构。Composite使得用户对单个对象和组合对象的使用具有一致性。

### JDK中的使用
- java.util.Map#putAll(java.util.Map)
- java.util.List#addAll(java.util.Collection)
- java.util.Set#addAll(java.util.Collection)

### 代码实现
实现一个LinkedList  
```java
class Node<T> {
    protected Node<T> previous;
    protected T data;
    protected Node<T> next;
    public Node(Node<T> previous, T data, Node<T> next){
        this.previous = previous;
        this.data = data;
        this.next = next;
    }
}

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public boolean add(T data) {
        final Node<T> last = this.tail;
        final Node<T> current = new Node<>(last, data, null);
        this.tail = current;
        if (this.head == null) {
            this.head = current;
        } else {
            last.next = current;
        }
        this.size++;
        return true;
    }

    public T get(int index){
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = head;
        for(int i = 0; i < index; i ++){
            node = node.next;
        }

        return node.data;
    }

    public boolean remove(T data) {
        for (Node<T> i = this.head; i != null; i = i.next) {
            if ((data == null && i.data == null)
                || (data != null && data.equals(i.data))) {
                final Node<T> previous = i.previous;
                final Node<T> next = i.next;
                if(previous == null){
                    this.head = next;
                }else{
                    previous.next = next;
                    i.previous = null;
                }

                if(next == null){
                    this.tail = previous;
                }else{
                    next.previous = previous;
                    i.next = null;
                }
                i.data = null;
                this.size --;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
}
```

### 单元测试
```groovy
class LinkedListSpec extends Specification {
    def "linked list"() {
        given:
        def list = new LinkedList()
        expect:
        list != null
        list.size() == 0
        list.isEmpty()

        when:
        list.add(1)
        then:
        1 == list.size()
        1 == list.get(0)

        when:
        list.add(2)
        then:
        2 == list.size()
        2 == list.get(1)

        when:
        list.add(null)
        then:
        3 == list.size()
        null == list.get(2)

        when:
        def remove = list.remove(1)
        then:
        remove
        2 == list.size()

        when:
        remove = list.remove(332)
        then:
        !remove
        2 == list.size()

        when:
        remove = list.remove(null)
        then:
        remove
        1 == list.size()

        when:
        remove = list.remove(2)
        then:
        remove
        0 == list.size()
        list.isEmpty()
    }
}
```