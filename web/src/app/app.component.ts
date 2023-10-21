import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private http: HttpClient) {

    http.get("http://localhost:8080/api/cars").subscribe(resultado => { console.log("resultado", resultado) })
  }
  title = 'web';
}
