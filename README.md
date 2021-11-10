# Tutorial APAP
## Authors
* **Muhammad Alif Herdin Besila** - *1906399120* - *B*
---
## Tutorial 5
1. Apa itu Postman? Apa kegunaannya?<br />
Postman adalah aplikasi API tester buatan Postman, Inc. yang tersedia untuk Windows, Linux, maupun OSX. Kegunaan aplikasi ini adalah untuk mengakses dan memanipulasi API yang dibuat pada sebuah aplikasi. Hal ini dikarenakan browser kadang-kadang tidak memiliki support method selain GET pada address bar maupun akses yang dibutuhkan untuk mengakses API tersebut, sehingga dibuatlah aplikasi ini untuk membantu developer dalam membuat API.

2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty.
Anotasi @JsonIgnoreProperties yang menghindari serialisasi atribut tertentu ini dapat berguna untuk menghindari Stackoverflow yang terjadi akibat pembacaan atribut yang saling mereferensi satu sama lain seperti pada kasus Agensi dan Tour Guide. Selanjutnya @JsonProperty merupakan anotasi yang berguna untuk menentukan nama yang digunakan pada serialisai/deserialisasi (seperti atribut) pada JSON yang digunakan, sehingga nama atribut dapat diatur sesuai keinginan.

3. Apa kegunaan atribut WebClient?
WebClient merupakan atribut yang berguna untuk melakukan HTTP request pada java. Atribut ini dapat digunakan untuk melakukan request ke sebuah server dan menerima balasannya sebagai sebuah variabel dan diolah pada aplikasi spring yang dibuat.

4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?
ResponseEntity merupakan atribut yang berguna untuk menampilkan dan memanipulasi http response yang dikirim dari sebuah server ataupun API seperti pesan yang *custom* pada body dari response tersebut. Sedangkan BindingResult merupakan atribut yang dapat membaca apabila terdapat error pada request ke API seperti field yang error pada sebuah form dan berguna untuk memvalidasi input yang dimasukkan pengguna ke API.

<br /><br />
https://stackoverflow.com/questions/10413886/what-is-the-use-of-bindingresult-interface-in-spring-mvc/36715053 <br />
https://www.baeldung.com/spring-response-entity <br />
https://www.baeldung.com/jackson-annotations <br />
https://www.baeldung.com/jackson-ignore-properties-on-serialization <br />
https://www.postman.com/

---
## Tutorial 4
1. Jelaskan perbedaan th:include dan th:replace!<br />
Perbedaan antara include dan replace adalah jika include hanya menyisipkan isi dari fragment kedalam sebuah elemen dari template, sedangkan replace menimpa semua isi dari elemen template tersebut dengan isi dari fragment

2. Jelaskan apa fungsi dari th:object!<br />
th:object merupakan object sebagai representasi dari form di html untuk dibaca oleh controller.

1. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai? <br />
penggunaan `*` pada syntax th:object adalah cara untuk menentukan elemen dari object yang telah didefinisikan oleh `$` dengan contoh jika objek Destinasi telah didefinisikan oleh `$` pada sebuah tabel, maka kolom Negara pada destinasi tersebut dapat ditampilkan dengan menggunakan `*`.
<br /><br />
https://stackoverflow.com/questions/37103958/difference-between-thymeleaf-include-and-replace <br />
https://frontbackend.com/thymeleaf/working-with-forms-in-thymeleaf <br />
https://stackoverflow.com/questions/57511424/difference-between-and
---
## Tutorial 3
1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)<br />
@AllArgsConstructor = anotasi untuk membuat konstruktor otomatis dengan 1 argumen parameter setiap fieldnya<br />
@NoArgsConstructor = anotasi untuk membuat konstruktor otomatis tanpa parameter pada field<br />
@Setter = anotasi membuat membuat method setter secara otomatis<br />
@Getter = anotasi membuat membuat method getter secara otomatis<br />
@Entity = anotasi yang dibutuhkan untuk membuat sebuah entity JPA<br />
@Table = anotasi untuk mendefinisikan class yang akan digunakan sebagai table pada database

2. Pada class TravelAgensiDb, terdapat method findByNoAgensi, apakah kegunaan dari method tersebut?<br />
   Method tersebut digunakan untuk mencari data agensi (berupa model) dengan paramter nomor agensi.

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn<br />
   **JoinTable** merupakan anotasi relationship yang menghubungkan relasi satu dan yang lainnya menggunakan column dari tabel itu sendiri, sedangkan **JoinColumn** akan menggunakan tabel baru untuk menghubungkan kedua relasi tersebut.

4. Pada class TourGuideModel, digunakan anotasi @JoinColumn pada atribut agensi, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull.<br />
   Kegunaan dar **name** adalah untuk menspesifikasikan sebuah kolom pada tabel, dan **referencedColumnName** menunjukkan kolom pada tabel lain yang menjadi referensi oleh kolom tadi. **nullable** adalah syntax yang menspesifikasikan sebuah kolom bisa berisi data Null atau tidak, dan bedanya dengan **@NotNull** adalah **@NotNull** bisa digunakan untuk validasi data yang dimasukkan sedangkan **nullable** hanya digunakan saat menspesifikasikan kolom.

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER<br />
   FetchType.LAZY = specifier ketika sebuah data dari sebuah table diambil, data terkait dari child relationnya hanya diambil ketika diperlukan.<br />
   CascadeType.ALL = menyalakan mode cascade pada semua operasi (PERSIST, REMOVE, REFRESH, MERGE, DETACH) data pada database.<br />
   FetchType.EAGER = specifier ketika sebuah data dari sebuah table diambil, data terkait dari child relationnya akan diambil berbarengan.

https://stackoverflow.com/questions/13027214/what-is-the-meaning-of-the-cascadetype-all-for-a-manytoone-jpa-association <br />
https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api <br />
https://www.baeldung.com/jpa-cascade-types <br />
https://www.baeldung.com/hibernate-notnull-vs-nullable <br />
https://stackoverflow.com/questions/29332907/what-is-the-exact-meaning-of-the-jpa-entity-annotation <br />
https://projectlombok.org/features/constructor

---
## Tutorial 2
1. **Cobalah untuk menambahkan sebuah Agensi dengan mengakses link berikut: http://localhost:8080/agensi/add?idAgensi=1&namaAgensi=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.** <br />
   Yang terjadi adalah munculnya halaman error berikut: <br />
   ![alt text](https://i.ibb.co/zP14W8F/a.png) <br />
   ![alt text](https://i.ibb.co/bFgCBB0/b.png) <br />
   Hal ini dikarenakan template yang digunakan (add-agensi.html) untuk menampilkan halaman Add Agensi belum dibuat.

2. **Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat.**
   Anotasi @Autowired merupakan anotasi yang memungkinkan spring untuk melakukan dependency injection secara otomatis. Cara kerjanya adalah anotasi ini akan melakukan auto-scanning dimana class yang ingin dijadikan Bean berada dibawah anotasi dan package yang tepat, dan spring akan mencari class-class tersebut dan menjadikan Bean secara otomatis. Dengan adanya anotasi ini, kita tidak perlu melakukan inisiasi dari class yang menjadi bean tersebut sehingga anotasi ini menerapkan Dependency Injection.
    

3. **Pertanyaan 3: Cobalah untuk menambahkan sebuah Agensi dengan mengakses link berikut: http://localhost:8080/agensi/add?idAgensi=1&namaAgensi=Papa%20APAP&alamat=Maung%20Fasilkom Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.** <br />
   Yang terjadi adalah munculnya halaman ini: <br />
   ![alt text](https://i.ibb.co/rwPPJN6/a.png) <br />
   ![alt text](https://i.ibb.co/xqshM5z/b.png) <br />
   Hal tersebut terjadi karena tidak adanya parameter yang dibutuhkan yaitu nomor telepon agensi untuk dimasukkan.

4. **Pertanyaan 4: Jika Papa APAP ingin melihat Travel Agensi dengan nama Papa APAP, link apa yang harus diakses?**
   Jika mencari hanya berdasarkan nama, maka berdasarkan program yang sudah dibuat, tidak ada cara untuk melihat travel agensi yang diinginkan. Sedangkan jika ingin mencari nama dan diketahui ID agensinya, maka hal tersebut dapat dilakukan dengan mencari berdasarkan ID tersebut dengan link seperti berikut: http://localhost:8080/agensi/view/idAgensi/{ID_AGENSI}

5. **Pertanyaan 5: Tambahkan 1 contoh Travel Agensi lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/agensi/viewAll, apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.**
   Saya menambahkan agensi dengan detail seperti berikut: <br />
   ![alt text](https://i.ibb.co/WztMG9C/a.png) <br />
   Kemudian saya membuka link ViewAll sepert pada pertanyaan di atas dan berikut adalah tampilan yang muncul: <br />
   ![alt text](https://i.ibb.co/zP3w5kj/b.png) <br />
   

**SUMBER:** <br />
https://stackoverflow.com/questions/19414734/understanding-spring-autowired-usage


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