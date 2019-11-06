package COURSE;

import any_structures.ArrayStack;
import any_structures.LinkedListStack;
import structuretools.Array;

import java.util.Scanner;

class Expression {
    public boolean flag;

    private boolean isNum(char a) {
        return (Character.isDigit(a));
    }

    private boolean isOpe(char a) {
        if (a == '+' || a == '-' || a == '*' || a == '/') {
            return true;
        }
        return false;
    }

    private boolean correct(Array c1) {
        ArrayStack<Character> stack = new ArrayStack<>();
        int i = 0;
        this.flag = true;
        while ((char) c1.get(i) != '#') {
            if ((char) c1.get(i) == '(') {
                stack.push((char) c1.get(i));
                flag = false;
            }
            if ((char) c1.get(i) == ')') {
                stack.pop();
                flag = true;
            }
            i++;
        }
        return flag;
    }

    private boolean rank(char a, char b) { // 优先级 a > b ? true : false;
        return (a == '*' || a == '/') && (b == '+' || b == '-');
    }


    private void trans(Array c1, Array c2) {
        int i = 0;
        char e;
        char ch;
        LinkedListStack<Character> stack = new LinkedListStack<>();
        while ((ch = (char) c1.get(i)) != '#') {
            if (isNum(ch)) {
                c2.addLast(ch);
            } else {
                if (ch == '(') {
                    stack.push(ch);
                }
                if ((ch == ')')) {
                    while ((e = stack.pop()) != '(' && !rank(e, ch)) {
                        c2.addLast(e);
                    }
                }
                if (isOpe(ch)) {
                    if (!stack.isEmpty() && rank(stack.peek(), ch)) {
                        while (!stack.isEmpty() && rank(stack.peek(), ch)) {
                            c2.addLast(stack.pop());
                        }
                    }
                    if (stack.isEmpty() || !rank(stack.peek(), ch)) {
                        c2.addLast(',');
                        stack.push(ch);
                    }

                }
            }
            i++;
        }
        while (!stack.isEmpty()) {
            c2.addLast(stack.pop());
        }
    }

    private String answer(Array c2) {  //11,22,33*+#
        LinkedListStack<String> stack = new LinkedListStack<>();
        StringBuilder e = new StringBuilder();
        int i = 0;
        char ch;
        while ((ch = (char) c2.get(i)) != '#') {
            if (isNum(ch)) {
                e.append(ch);
            }
            if (ch == ',' || isOpe((char) c2.get(i + 1))) {
                stack.push(e.toString().trim());
                if (stack.peek().equals("")) {
                    stack.pop();
                }
                e.setLength(0); //清空内容
            }
            if (isOpe(ch)) {
                double b = Double.parseDouble(stack.pop());
                double a = Double.parseDouble(stack.pop());
                switch (ch) {
                    case '+':
                        stack.push(String.valueOf(a + b));
                        break;
                    case '-':
                        stack.push(String.valueOf(a - b));
                        break;
                    case '*':
                        stack.push(String.valueOf(a * b));
                        break;
                    case '/':
                        stack.push(String.valueOf(a / b));
                        break;
                }
            }
            i++;
        }
        return stack.pop();
    }


    public static void main(String[] args) {
        Array<Character> c1 = new Array<>();
        Array<Character> c2 = new Array<>();
        Expression exp = new Expression();
        Scanner sc = new Scanner(System.in);
        String scC1 = sc.next();
        for (int i = 0; i < scC1.length(); i++) {
            c1.addLast(scC1.charAt(i));
        }
        System.out.println("您输入的内容为：" + c1);

        if (!exp.correct(c1)) {
            throw new IllegalArgumentException("请注意，您输入的表达式括号不匹配");
        }

        exp.trans(c1, c2);
        System.out.println("转后缀表达式为" + c2);
        c2.addLast('#');
        System.out.println("表达式值为" + exp.answer(c2));
    }

}