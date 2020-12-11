import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ClinicaAdaptrojhAppTestModule } from '../../../test.module';
import { EspecialidadeSaudeDetailComponent } from 'app/entities/especialidade-saude/especialidade-saude-detail.component';
import { EspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';

describe('Component Tests', () => {
  describe('EspecialidadeSaude Management Detail Component', () => {
    let comp: EspecialidadeSaudeDetailComponent;
    let fixture: ComponentFixture<EspecialidadeSaudeDetailComponent>;
    const route = ({ data: of({ especialidadeSaude: new EspecialidadeSaude(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ClinicaAdaptrojhAppTestModule],
        declarations: [EspecialidadeSaudeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EspecialidadeSaudeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EspecialidadeSaudeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load especialidadeSaude on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.especialidadeSaude).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
