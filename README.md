# Tutorial APAP
## Authors
* **Muhammad Alif Herdin Besila** - *1906399120* - *B*

---
## Tutorial 1
### What I have learned today
(Masukkan pertanyaan yang diikuti jawaban di setiap nomor, contoh seperti dibawah. Anda
juga boleh menambahkan catatan apapun di bagian ini)
### Github
1. **Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?**
Issue tracker digunakan untuk men-track masalah2 seperti bug atau permintaan fitur oleh pengguna atau developer lain
2. **Apa perbedaan dari git merge dan git merge --squash?** Perbedaannya adalah squash tidak membawa history dari branch fitur (hanya memiliki perubahan file), sedang kan tanpa squash akan membawa history dari branch fitur
3. **Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan
suatu aplikasi?** Meng-track perubahan dan versi dari development aplikasi, dan mempermudah dalam pengerjaan aplikasi secara tim.
### Spring
1. **Apa itu library & dependency?** Library adalah kumpulan kode yang dibuat untuk digunakan berkali-kali dan bertujuan untuk membawa fungsional tertentu di sebuah project. Dependency adalah library ketika digunakan di sebuah project. 
2. **Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?** Maven adalah tool untuk melakukan otomatisasi dan mempermudah eksekusi project. Alasan kita menggunakan Maven adalah karena sebuah project untuk di run seringkali membutuhkan beberapa proses dan command untuk dilakukan, selain itu juga karena sebuah project terkadang memerlukan berbagai dependency dan penggunaan Maven mempermudah untuk *memasukkan* dependency tersebut kedalam project untuk dieksekusi. Contoh alternatif dari Maven adalah Gradle.
3. **Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring
framework?** Yang bisa dikembangkan menggunakan Spring seperti aplikasi enterprise desktop Java. Contohnya adalah data mining software. 
7. **Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
menggunakan @RequestParam atau @PathVariable?** @RequestParam menggunakan input berupa parameter yang disisipkan kedalam URI, sedangkan @PathVariable menggunakan URI itu sendiri sebagai input. @RequestParam sebaiknya digunakan untuk kegunaan filter atau pencarian, sedangkan @PathVariable sebaiknya digunakan untuk mengakses data berdasarkan elemen tertentu seperti nomor index.