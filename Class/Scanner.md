# Scanner 사용시 유의 사항들

## 1. next() nextline() nextInt() method

  - next()
    - 개행문자를 무시하고 사이의 단어(토큰)들을 받고, '\n'들을 냅둔다.
    - \n a b \n 입력이 들어간 상태에서 next() 메소드를 실행시키면, a b 문자를 받는다.
    
  - nextLine()
    - 개행을 포함해서, 개행까지의 문자들을 받고, 그 뒤의 '\n'을 버리고 나머지만 리턴
    - \n a b \n 입력이 들어갔다면, \n 만 받는다.
    - ab cd e f \n 입력이 들어갔다면, ab cd e f \n 까지 들어간다.
  
  - nextInt()
    - 개행문자를 무시하고 사이의 정수를 받는다.
    
  - nextInt() 와 nextLine() 을 같이 사용할 때 문제가 발생할 수 있다.
    - Integer.parseInt(scanner.nextLine())을 사용하면 \n(개행문자)로 발생하는 문제 미연에 방지 가능.
  