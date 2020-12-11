import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { ClinicaAdaptrojhAppTestModule } from '../../../test.module';
import { FuncionarioDetailComponent } from 'app/entities/funcionario/funcionario-detail.component';
import { Funcionario } from 'app/shared/model/funcionario.model';

describe('Component Tests', () => {
  describe('Funcionario Management Detail Component', () => {
    let comp: FuncionarioDetailComponent;
    let fixture: ComponentFixture<FuncionarioDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ funcionario: new Funcionario(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ClinicaAdaptrojhAppTestModule],
        declarations: [FuncionarioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FuncionarioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FuncionarioDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load funcionario on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.funcionario).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
