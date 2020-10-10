import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Categoria } from '../model/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService  {
  URL = 'http://localhost:8080/categorias';

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getCategorias(): Observable<Categoria[]> {
    return this.httpClient.get<Categoria[]>(this.URL)
      .pipe(
        retry(2),
        catchError(this.handleError));
  }

  getCategoria(id: number): Observable<Categoria> {
    return this.httpClient.get<Categoria>(this.URL + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError));
  }

  saveCategoria(categoria: Categoria): Observable<Categoria> {
    return this.httpClient.post<Categoria>(this.URL, JSON.stringify(categoria), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

  updateCategoria(categoria: Categoria): Observable<Categoria> {
    return this.httpClient.put<Categoria>(this.URL + '/' + categoria.id, JSON.stringify(categoria), this.httpOptions)
      .pipe(retry(1),
        catchError(this.handleError)
      );
  }
  deleteCategoria(categoria: Categoria) {
   return this.httpClient.delete<Categoria>(this.URL + '/' + categoria.id, this.httpOptions)
      .pipe(retry(1),
        catchError(this.handleError)
      );
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.error.message}`;
    }
    return throwError(errorMessage);
  }
}
