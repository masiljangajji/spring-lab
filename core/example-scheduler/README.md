## DB로 In-Memory 를 사용합니다. (인프라 의존성을 없애 누구나 쉽게 실행 가능하도록)

- 따라서 `ShedLock`, `@Transectional` 은 구현하지 않고 언급만 합니다.
- DIP와 프로파일 기반으로 의존성을 분리해 `RDBMS`, `JPA` 기술 추가를 원할시 구현체만 갈아끼면 됩니다.

## 비즈니스 규칙

- 개인정보보호법에 의해서 목적이 다한 사용자 정보는 주기적으로 삭제되어야 합니다.
- 이를 스케줄러를 이용해 처리할 예정입니다.

다음의 경우에 대해서 다룹니다.

1. `Scheduler` 실패시 `deleteUserCreatedBeforeJob` 재실행, @Transectional 로 인해 롤백시 전체를 재시도
2. `TryCatch Scheduler` 실패시 `deleteUserCreatedBeforeJobWithTryCatch` 재실행
    - 여러개의 쿼리를 보내는 경우 Ex) JPA DeleteAll, Service 로직을 try-catch 로 감싸 커밋 후 처리 -> 실패하는 지점부터 재시도 가능 함
    - 하지만 벌크작업(하나의 쿼리)의 경우에는 여전히 전체가 재시도 됨
3. `Chunk Scheduler` 실패시 `deleteUserCreatedBeforeJobWithChunks` 재실행
    - 벌크작업(하나의 쿼리)에 대해 청크를 나눔으로써 작업을 분산 -> 실패하는 지점부터 재시도
    - 여러개의 쿼리를 날리지만 작업이 청크 단위로 묶임으로서 보다 효율적
    - 삭제가 필요한 ids 를 메모리에 저장해야 되기 때문에 메모리에 부담이 생김
    - 따라서 페이징해서 가져오거나 커서를 사용해야 함 -> 메모리 최적화 하면서 사용 가능
4. 기본 실행 스케줄러는 `Scheduler`, 사용이 햇갈릴시 `application-local.yml` 참조
