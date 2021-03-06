import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FornecedorService } from 'app/entities/fornecedor/fornecedor.service';
import { IFornecedor, Fornecedor } from 'app/shared/model/fornecedor.model';
import { TipoPessoa } from 'app/shared/model/enumerations/tipo-pessoa.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

describe('Service Tests', () => {
  describe('Fornecedor Service', () => {
    let injector: TestBed;
    let service: FornecedorService;
    let httpMock: HttpTestingController;
    let elemDefault: IFornecedor;
    let expectedResult: IFornecedor | IFornecedor[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FornecedorService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Fornecedor(
        0,
        TipoPessoa.F,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Estado.AC,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Fornecedor', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Fornecedor()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Fornecedor', () => {
        const returnedFromService = Object.assign(
          {
            tipoPessoa: 'BBBBBB',
            numeroCPF: 'BBBBBB',
            numeroCNPJ: 'BBBBBB',
            numeroInscricaoEstadual: 'BBBBBB',
            nome: 'BBBBBB',
            nomeFantasia: 'BBBBBB',
            email: 'BBBBBB',
            telefone1: 'BBBBBB',
            telefone2: 'BBBBBB',
            logradouroNome: 'BBBBBB',
            logradouroNumero: 'BBBBBB',
            logradouroComplemento: 'BBBBBB',
            bairro: 'BBBBBB',
            cep: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            observacao: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Fornecedor', () => {
        const returnedFromService = Object.assign(
          {
            tipoPessoa: 'BBBBBB',
            numeroCPF: 'BBBBBB',
            numeroCNPJ: 'BBBBBB',
            numeroInscricaoEstadual: 'BBBBBB',
            nome: 'BBBBBB',
            nomeFantasia: 'BBBBBB',
            email: 'BBBBBB',
            telefone1: 'BBBBBB',
            telefone2: 'BBBBBB',
            logradouroNome: 'BBBBBB',
            logradouroNumero: 'BBBBBB',
            logradouroComplemento: 'BBBBBB',
            bairro: 'BBBBBB',
            cep: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            observacao: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Fornecedor', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
