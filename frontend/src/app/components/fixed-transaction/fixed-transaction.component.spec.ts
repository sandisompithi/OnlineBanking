import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FixedTransactionComponent } from './fixed-transaction.component';

describe('FixedTransactionComponent', () => {
  let component: FixedTransactionComponent;
  let fixture: ComponentFixture<FixedTransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FixedTransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FixedTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
