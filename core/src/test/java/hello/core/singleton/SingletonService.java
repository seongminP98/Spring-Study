package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    //static으로 되어있으면 static 영역에 하나만 만들어져서 올라간다.

    public static SingletonService getInstance() { //이 메서드로만 SingletonService 객체를 조회할 수 있다.
        return instance;
    }

    private SingletonService() {  //생성자를 private로 만들어 다른곳에서 객체를 생성하지 못하도록 한다.
    }

    public void logic() {
        System.out.println("싱글콘 객체 로직 호출");
    }


}
