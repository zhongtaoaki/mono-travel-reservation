# mono-app

### データベース

ホテルサービスとフライトサービスは現時点での状況：
- テーブル一個しか持っていない
- 一つのフライトで座席は一つしか持っていない、更に座席番号は固定
- 一つのホテルで部屋は一つしか持っていない、更に部屋番号は固定

### 仕様通り実現できていない機能
- 各サービスのエラー処理
    - 現時点全部springbootの通常エラーにしている。カスタマイズHTTｐレスポンスにはなっていない。

### 仕様変更
- フライトサービス
    - データベースで検索しやすいように、LocalDateからLocalDateTimeに変換せずに、エンティティFlightReserveに下記を追加
        - LocalDate departureDate

- ホテルサービス
    - データベースで検索しやすいように、LocalDateからLocalDateTimeに変換せずに、エンティティHotelReservationに下記を追加
        - LocalDate checkInDate
        - LocalDate checkOutDate

- 予約サービス
    - JPAでエンティティjoinのポリモーフィズム実現できていないので、エンティティ「Itinerary」に下記を修正
        ```java
        @ElementCollection
        private List<Reservation> reservations;
        ```
        ↓
        ```java
        @ElementCollection
        private List<HotelReservation> hotelReservations;

        @ElementCollection
        private List<FlightReservation> flightReservations;
        ```
    - 下記のエラー解決できていないので、Itineraryのtouristsをコメントアウトにしました。
        ```java
        HibernateException: Found shared references to a collection: com.example.msasample.mono.travelreservation.model.entities.Itinerary.tourists
        ```
