## CheckTime
  CheckTimeが、指定した範囲内に含まれるかどうかを調べるアプリです。CheckTimeは、MVVMアーキテクチャ、Hilt依存性注入、Roomデータベース、LiveData、コルーチン、およびその他のモダンなAndroid開発の使用方法を示すデモアプリです。
  以下の条件を満たすこと。
- ある時刻と、時間の範囲(開始時刻と終了時刻)を受け取る。
- 時刻は時間を整数で指定すること。(6 時であれば 6)
- 範囲指定は、開始時刻を含み、終了時刻は含まないと判断すること。
- ただし開始時刻と終了時刻が同じ場合は含むと判断すること。
- 開始時刻が 22 時で終了時刻が 5 時、というような指定をされても動作すること。
- 結果をデバイスに保存することができるようにすること。
- 開始時刻、終了時刻、ある時刻と判断結果
- 結果一覧を表示できるようにすること。

## 動作デモ
![output](https://github.com/user-attachments/assets/f7db6271-bfc2-4c1c-94e5-aa2dd33f9cbd)


## アーキテクチャ
![fb8ed8045751bfd4811d6c7ab4e5f9b](https://github.com/user-attachments/assets/d15b14c2-fafc-43ef-a3b2-10f9ceb1fa64)

## テスト
Roomデータベースのインストゥルメンテッドテストケースを実装しています。
