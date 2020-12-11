import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ClinicaAdaptrojhAppTestModule } from '../../../test.module';
import { PlanoEstrategicoDetailComponent } from 'app/entities/plano-estrategico/plano-estrategico-detail.component';
import { PlanoEstrategico } from 'app/shared/model/plano-estrategico.model';

describe('Component Tests', () => {
  describe('PlanoEstrategico Management Detail Component', () => {
    let comp: PlanoEstrategicoDetailComponent;
    let fixture: ComponentFixture<PlanoEstrategicoDetailComponent>;
    const route = ({ data: of({ planoEstrategico: new PlanoEstrategico(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ClinicaAdaptrojhAppTestModule],
        declarations: [PlanoEstrategicoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PlanoEstrategicoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PlanoEstrategicoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load planoEstrategico on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.planoEstrategico).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
