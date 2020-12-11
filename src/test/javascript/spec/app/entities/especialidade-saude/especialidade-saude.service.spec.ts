import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EspecialidadeSaudeService } from 'app/entities/especialidade-saude/especialidade-saude.service';
import { IEspecialidadeSaude, EspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';

describe('Service Tests', () => {
  describe('EspecialidadeSaude Service', () => {
    let injector: TestBed;
    let service: EspecialidadeSaudeService;
    let httpMock: HttpTestingController;
    let elemDefault: IEspecialidadeSaude;
    let expectedResult: IEspecialidadeSaude | IEspecialidadeSaude[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EspecialidadeSaudeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EspecialidadeSaude(0, 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EspecialidadeSaude', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EspecialidadeSaude()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EspecialidadeSaude', () => {
        const returnedFromService = Object.assign(
          {
            descricao: 'BBBBBB',
            sigla: 'BBBBBB',
            ativo: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EspecialidadeSaude', () => {
        const returnedFromService = Object.assign(
          {
            descricao: 'BBBBBB',
            sigla: 'BBBBBB',
            ativo: true,
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

      it('should delete a EspecialidadeSaude', () => {
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
