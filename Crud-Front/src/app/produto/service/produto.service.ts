import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  URL = 'http://localhost:8080/produtos';

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getProdutos(): Observable<Produto[]> {
    return this.httpClient.get<Produto[]>(this.URL)
      .pipe(
        retry(2),
        catchError(this.handleError));
  }

  getProduto(id: number): Observable<Produto> {
    return this.httpClient.get<Produto>(this.URL + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError));
  }

  saveProduto(produto: Produto): Observable<Produto> {
    return this.httpClient.post<Produto>(this.URL, JSON.stringify(produto), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

  updateProduto(produto: Produto): Observable<Produto> {
    return this.httpClient.put<Produto>(this.URL + '/' + produto.id, JSON.stringify(produto), this.httpOptions)
      .pipe(retry(1),
        catchError(this.handleError)
      );
  }
  deleteProduto(produto: Produto) {
   return this.httpClient.delete<Produto>(this.URL + '/' + produto.id, this.httpOptions)
      .pipe(retry(1),
        catchError(this.handleError)
      );
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = error.error.message;
    }
    return throwError(errorMessage);
  }

}
