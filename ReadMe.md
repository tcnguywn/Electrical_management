Quản lý điện năng tiêu thụ v1 : 
  Link http://localhost:8080/electrical_management
- Link thang điện tham khảo : https://zalopay.vn/1-so-dien-bao-nhieu-tien-3973
- Api TierConfig : Quản lý thang giá điện.
    - Các thành phần :
      - idTier : mã được generate tự động theo thứ tự (nhưng đang bị lỗi là xuất phát từ 9 mà chưa tìm ra nguyên nhân).
      - minVal : giới hạn dưới của bậc giá.
      - maxVal : giới hạn trên của bậc giá.(thang đo cuối không có giới hạn trên , em có thử đặt thành Long và cho bằng null nhưng có lỗi nên em để lại là 10^9 với kiểu long)
      - pricePerUnit : giá điện trên mỗi bậc.
    - Có các thao tác đơn giản CRUD (được gọi theo ID).
    - Link : http://localhost:8080/electrical_management/tier
      - Body cho Create :
        {
        "minVal": 400,
        "maxVal": 1000000,
        "pricePerUnit": 3.151
        }

- Api UsageHistory : Quản lý lịch sử sử dụng điện.
  - Các thành phần:
    - idUsage : mã được generate tự động theo thứ tự.
    - date : ngày tháng năm sử dụng điện.
    - unitsUsed : số điện được dùng trong ngày.
    - amount : quy đổi số điện ra tiền theo thang giá sử dụng.
  - Có các thao tác CRUD :
    - Delete : theo ID.
    - Update : Cập nhật giá trị số điện của ngày.
    - Read : có thể đọc theo ngày , id hoặc đọc tất cả.
  - Link : http://localhost:8080/electrical_management/usage-history
    - Create : như trên
    - Read : như trên thì Read tất cả
      - link + /date/'ngày muốn tìm' hoặc /id tương ứng nếu muốn tìm theo ngày hoặc id
    - Update : link + /date/'ngày muốn cập nhật'
    - Delete : link + /id
    - Body Create :
      {
      "date": "2024-11-11",
      "unitsUsed": 354
      }

