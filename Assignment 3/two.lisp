;calculate 4x^2 for any number x
(defun square (x) (* x x 4))
;calculate 4x^4 -4x^3 for any number x
(defun thi (x) (- (* x x x x 4) (* x x x 4)))
;calculate 4e^x for any number x
(defun sec (x) (* 4 (exp x)))


;simpsons method for calculating the area of the curve
(defun simpsons (f a b n)

  ;division of strips
  (setq h  (/ (- b a) n))
  
  ;calculate f(x) for every kth strip
  (defun yk (k)
  (funcall f (+ a (* k h)))
  )
  
  ;calculating f(a) and f(b)
  (setq s (+ (yk 0) (yk n))) 
  
  ;for odd number strip
  (loop for i from 1 to (- n 1)
    if(oddp i)
    do(setq s (+ s (* 4 (yk i))))
  )
  
  ;for even number strip
  (loop for i from 2 to (- n 2)
    if(evenp i)
    do(setq s (+ s (* 2 (yk i))))
  )
  (/ (* h s) 3.0)

)

;trapezoid method for calculating the area of curve
(defun trapezoid (f a b n)
  ;division of strips
  (setq h  (/ (- b a) n))

  ;calculate f(x) for every kth strip
  (defun yk (k)
  (funcall f (+ a (* k h)))
  )

  ;calculating f(a) and f(b)
  (setq s (+ (yk 0) (yk n)))
  (loop for i from 1 to (- n 1)
    do(setq s (+ s (* 2 (yk i))))
  )

  (/ (* h s) 2.0)

)

;printing value for all the designs
(print 'Square-simpsons)
(print (simpsons 'square 2 4 100))
(print 'exp-simpsons)
(print (simpsons 'thi 2 4 100))
(print 'Third-simpsons)
(print (simpsons 'sec 2 4 100))
(terpri)
(format t "minimum among simpsons")
;minimum among all the designs in simpsons
(print (min (simpsons 'square 2 4 100) (simpsons 'thi 2 4 100) (simpsons 'sec 2 4 100) ))
(print 'Square-trapezoid)
(print (trapezoid 'square 2 4 100))
(print 'exp-trapezoid)
(print (trapezoid 'thi 2 4 100))
(print 'Third-trapezoid)
(print (trapezoid 'sec 2 4 100))
(terpri)
;minimum among all the designs in trapezoid
(format t "minimum among trapezoid")
(print (min (trapezoid 'square 2 4 100) (trapezoid 'thi 2 4 100) (trapezoid 'sec 2 4 100) ))
